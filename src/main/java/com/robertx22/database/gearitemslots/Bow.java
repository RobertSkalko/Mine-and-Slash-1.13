package com.robertx22.database.gearitemslots;

import com.robertx22.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorPeneFlat;
import com.robertx22.database.stats.stat_mods.percent.offense.ArmorPenePercent;
import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.items.gearitems.weapon_mechanics.BowWeaponMechanic;
import com.robertx22.items.gearitems.weapons.ItemBow;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Bow extends BaseWeapon {

    @Override
    public String GUID() {
        return "Bow";
    }

    @Override
    public Item DefaultItem() {
        return ItemBow.Items.get(0);
    }

    @Override
    public List<StatMod> slotTypeStats() {
        return Arrays.asList(new ArmorPenePercent(), new ArmorPeneFlat());
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ItemBow.Items;
    }

    @Override
    public WeaponMechanic mechanic() {
        return new BowWeaponMechanic();
    }
}
