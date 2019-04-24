package com.robertx22.customitems.gearitems.bases;

import com.robertx22.customitems.gearitems.bases.armor_materials.CommonMat;
import com.robertx22.customitems.gearitems.bases.armor_materials.EpicMat;
import com.robertx22.customitems.gearitems.bases.armor_materials.LegendaryMat;
import com.robertx22.customitems.gearitems.bases.armor_materials.MythicalMat;
import com.robertx22.customitems.gearitems.bases.armor_materials.RareMat;
import com.robertx22.customitems.gearitems.bases.armor_materials.UncommonMat;
import com.robertx22.customitems.gearitems.bases.armor_materials.UniqueMat;
import com.robertx22.database.rarities.items.UniqueItem;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemArmor;

public abstract class BaseArmorItem extends ItemArmor implements IGearItem {

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