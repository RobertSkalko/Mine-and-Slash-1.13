package com.robertx22.items.gearitems.bases;

import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.items.gearitems.bases.armor_materials.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemArmor;

public abstract class BaseArmorItem extends ItemArmor {

    public static int MAX_GEAR_DURABILITY = 1000;

    static int Enchantability = 10;

    public abstract String Name();

    public BaseArmorItem(int rarity, EntityEquipmentSlot slot) {

        super(GetMat(rarity), slot, new Properties());

    }

    private static IArmorMaterial GetMat(int rarity) {

        if (rarity == 0) {
            return new CommonMat();
        } else if (rarity == 1) {
            return new UncommonMat();
        } else if (rarity == 2) {
            return new RareMat();
        } else if (rarity == 3) {
            return new EpicMat();
        } else if (rarity == 4) {
            return new LegendaryMat();
        } else if (rarity == 5) {
            return new MythicalMat();
        } else if (rarity == new UniqueItem().Rank()) {
            return new UniqueMat();
        }

        return new CommonMat();

    }

}