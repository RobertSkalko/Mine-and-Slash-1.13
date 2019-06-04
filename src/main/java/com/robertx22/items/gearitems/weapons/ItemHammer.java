package com.robertx22.items.gearitems.weapons;

import com.robertx22.db_lists.Rarities;
import com.robertx22.items.gearitems.bases.BaseWeaponItem;
import com.robertx22.items.gearitems.bases.IWeapon;
import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.items.gearitems.weapon_mechanics.HammerWeaponMechanic;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import net.minecraft.item.Item;

import java.util.HashMap;

public class ItemHammer extends BaseWeaponItem implements IWeapon {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemHammer(int rar) {
        super(rar);
    }

    @Override
    public String locNameForLangFile() {
        Rarity rar = Rarities.Items.get(rarity);
        return rar.textFormatColor() + "Hammer";
    }

    @Override
    public WeaponMechanic mechanic() {
        return new HammerWeaponMechanic();
    }
}
