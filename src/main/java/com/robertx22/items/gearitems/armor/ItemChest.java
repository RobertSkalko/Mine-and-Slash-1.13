package com.robertx22.items.gearitems.armor;

import com.robertx22.db_lists.Rarities;
import com.robertx22.items.gearitems.bases.BaseArmorItem;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;

import java.util.HashMap;

public class ItemChest extends BaseArmorItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemChest(int rarity) {
        super(rarity, EquipmentSlotType.CHEST);

    }

    @Override
    public String Name() {
        return "Chest";
    }

    @Override
    public String locNameForLangFile() {
        Rarity rar = Rarities.Items.get(rarity);
        return rar.textFormatColor() + "Chest";
    }
}
