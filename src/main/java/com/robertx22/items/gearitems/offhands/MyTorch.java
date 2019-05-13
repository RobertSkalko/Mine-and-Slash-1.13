package com.robertx22.items.gearitems.offhands;

import com.robertx22.items.gearitems.bases.BaseSpellItem;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mod.EventBusSubscriber
public class MyTorch extends Item implements IEffectItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public MyTorch() {
        super(new Properties().maxStackSize(1).defaultMaxDamage(1000));
    }

    @Override
    public EnumAction getUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 20;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player,
                                                    EnumHand handIn) {
        ItemStack itemstack = player.getHeldItem(handIn);

        if (player.getHeldItemMainhand().getItem() instanceof BaseSpellItem) {
            return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        }

        player.setActiveHand(handIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn,
                                     EntityLivingBase player) {

        // stops using it when you want to right click a spell
        if (player.getHeldItemMainhand().getItem() instanceof BaseSpellItem) {
            return stack;
        }

        if (worldIn.isRemote == false) {

            EntityData.UnitData data = Load.Unit(player);

            float manarestored = restoreBasedOnMissing(data.getCurrentMana(), data.getUnit()
                    .manaData().Value);

            float energyrestored = restoreBasedOnMissing(data.getCurrentEnergy(), data.getUnit()
                    .energyData().Value);

            if (manarestored > energyrestored) {
                if (manarestored > 0) {
                    data.restoreMana(manarestored);
                }
            } else {
                if (energyrestored > 0) {
                    data.restoreEnergy(energyrestored);
                }
            }

            player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 3));
            player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 350, 2));
        } else {
            ParticleUtils.spawnEnergyRestoreParticles(player, 4);
            ParticleUtils.spawnManaRestoreParticles(player, 4);
            player.playSound(SoundEvents.AMBIENT_UNDERWATER_ENTER, 0.3F, 0);
        }

        return stack;
    }

    private float restoreBasedOnMissing(float current, float max) {

        float missing = max - current;

        if (missing > 20) {
            missing /= 20;
            return missing;
        }
        return 0;

    }

    @Override
    public List<ITextComponent> getEffectTooltip(boolean moreInfo) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new TextComponentString(""));
        list.add(new TextComponentString(color() + "" + TextFormatting.BOLD + "[Active]: " + TextFormatting.RESET + color() + "Restoration"));
        if (moreInfo) {
            list.add(new TextComponentString(color() + "Restores Mana/Energy Based on Missing Amount"));
        }
        return list;
    }
}