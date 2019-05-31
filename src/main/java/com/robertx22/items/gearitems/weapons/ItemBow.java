package com.robertx22.items.gearitems.weapons;

import com.robertx22.items.gearitems.bases.IWeapon;
import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.items.gearitems.weapon_mechanics.BowWeaponMechanic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;

public class ItemBow extends net.minecraft.item.ItemBow implements IWeapon {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemBow() {
        super(new Properties().maxStackSize(1).defaultMaxDamage(1000));

    }

    @Override
    public WeaponMechanic mechanic() {
        return new BowWeaponMechanic();
    }

    // faster loading

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getUseDuration(ItemStack stack) {
        return 40000;//// 72000;
    }

}
