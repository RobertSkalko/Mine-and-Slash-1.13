package com.robertx22.items.gearitems.weapons;

import com.robertx22.items.gearitems.MyEntityArrow;
import com.robertx22.items.gearitems.bases.BaseBow;
import com.robertx22.items.gearitems.bases.BaseWeaponItem;
import com.robertx22.items.gearitems.bases.IWeapon;
import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.items.gearitems.weapon_mechanics.BowWeaponMechanic;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.HashMap;

public class ItemBow extends BaseBow implements IWeapon {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemBow() {

    }

    @Override
    public String Name() {
        return "Bow";
    }

    int arrowCount = 3;

    @Override
    public WeaponMechanic mechanic() {
        return new BowWeaponMechanic();
    }

    // so bow can fire without arrows
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn,
                                                    EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean flag = true; // !this.findAmmo(playerIn).isEmpty();

        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
        if (ret != null)
            return ret;

        if (BaseWeaponItem.checkDurability(playerIn, itemstack) == false) {
            return flag ? new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack) : new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        } else {
            playerIn.setActiveHand(handIn);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn,
                                     EntityLivingBase entityLiving, int timeLeft) {
        if (entityLiving instanceof EntityPlayer) {

            EntityPlayer entityplayer = (EntityPlayer) entityLiving;
            boolean flag = entityplayer.isCreative() || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;

            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            // i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, flag);
            //if (i < 0)
            //   return;

            float f = getArrowVelocity(i);

            if ((double) f >= 0.1D) {
                boolean flag1 = entityplayer.isCreative();

                if (!worldIn.isRemote) {

                    stack.damageItem(1, entityplayer);

                    UnitData sourcedata = Load.Unit(entityplayer);

                    if (sourcedata.tryUseWeapon(sourcedata.getWeaponData(entityplayer), entityplayer) == false) {

                        return;

                    }
                    // else shoot

                    float rotaY = entityplayer.rotationYaw;

                    MyEntityArrow entityarrow = new MyEntityArrow(worldIn, entityplayer, sourcedata, stack);
                    entityarrow.shoot(entityplayer, entityplayer.rotationPitch, rotaY, 0.0F, f * 3F, 1F);

                    int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

                    if (k > 0) {
                        entityarrow.setKnockbackStrength(k);
                    }

                    if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                        entityarrow.setFire(100);
                    }

                    worldIn.spawnEntity(entityarrow);

                }

                worldIn.playSound((EntityPlayer) null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (this.random
                        .nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            }
        }

    }

    // faster loading

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack) {
        return 40000;//// 72000;
    }

}
