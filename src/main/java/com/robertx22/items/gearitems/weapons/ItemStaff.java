package com.robertx22.items.gearitems.weapons;

import com.robertx22.items.gearitems.bases.BaseWeaponItem;
import com.robertx22.items.gearitems.bases.IWeapon;
import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.items.gearitems.offhands.IEffectItem;
import com.robertx22.items.gearitems.weapon_mechanics.StaffWeaponMechanic;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.spells.bases.projectile.EntityStaffProjectile;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemStaff extends BaseWeaponItem implements IWeapon, IEffectItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemStaff() {

    }

    @Override
    public String Name() {
        return "Staff";
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
    public ItemStack onItemUseFinish(ItemStack stack, World world,
                                     EntityLivingBase player) {

        try {

            if (!world.isRemote) {

                if (checkDurability(player, stack)) {

                    UnitData data = Load.Unit(player);

                    data.recalculateStats(player, Load.World(player.world));

                    GearItemData weapondata = data.getWeaponData(player);

                    if (data.tryUseWeapon(weapondata, player)) {

                        EntityStaffProjectile projectile = new EntityStaffProjectile(world);
                        projectile.SetReady(stack);
                        projectile.SpawnAndShoot(null, null, player);

                        stack.damageItem(1, player);

                        SoundUtils.playSoundAtPlayer((EntityPlayer) player, SoundEvents.ENTITY_SNOWBALL_THROW, 1, 1);

                    }

                }

            }
        } catch (

                Exception e) {
            e.printStackTrace();
        }

        return stack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player,
                                                    EnumHand handIn) {
        ItemStack itemstack = player.getHeldItem(handIn);
        player.setActiveHand(handIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public WeaponMechanic mechanic() {
        return new StaffWeaponMechanic();
    }

    @Override
    public List<ITextComponent> getEffectTooltip(boolean moreInfo) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new TextComponentString(""));
        list.add(new TextComponentString(color() + "" + TextFormatting.BOLD + "[Active]: " + "Magic Projectile"));
        if (moreInfo) {
            list.add(new TextComponentString(color() + "Casts an orb that damages first enemy hit"));
        }
        return list;
    }
}