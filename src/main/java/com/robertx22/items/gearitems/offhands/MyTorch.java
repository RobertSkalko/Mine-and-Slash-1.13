package com.robertx22.items.gearitems.offhands;

import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;

@Mod.EventBusSubscriber
public class MyTorch extends Item {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public MyTorch() {
        super(new Properties().maxStackSize(1).defaultMaxDamage(1000));
    }

    @Override
    public EnumAction getUseAction(ItemStack stack) {
        return EnumAction.EAT;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 10;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player,
                                                    EnumHand handIn) {

        ItemStack itemstack = player.getHeldItem(handIn);
        player.setActiveHand(handIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn,
                                     EntityLivingBase player) {

        if (worldIn.isRemote == false) {

            EntityData.UnitData data = Load.Unit(player);

            float manarestored = restoreBasedOnMissing(data.getCurrentMana(), data.getUnit()
                    .manaData().Value);

            float energyrestored = restoreBasedOnMissing(data.getCurrentEnergy(), data.getUnit()
                    .energyData().Value);

            if (manarestored > 0) {
                data.restoreMana(manarestored);
            }

            if (energyrestored > 0) {
                data.restoreEnergy(energyrestored);
            }

            player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 350, 2));
            player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 350, 2));
        }

        return stack;
    }

    private float restoreBasedOnMissing(float current, float max) {

        float missing = max - current;

        if (missing > 20) {
            missing /= 35;
            return missing;
        }
        return 0;

    }
}