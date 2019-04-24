package com.robertx22.customitems.gearitems.bases.armor_materials;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;

public class EpicMat implements IArmorMaterial {

    @Override
    public int getDurability(EntityEquipmentSlot slotIn) {
	return 700;
    }

    @Override
    public int getDamageReductionAmount(EntityEquipmentSlot slotIn) {
	return 2;
    }

    @Override
    public int getEnchantability() {
	return 10;
    }

    @Override
    public SoundEvent getSoundEvent() {
	return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    @Override
    public Ingredient getRepairMaterial() {
	return null;
    }

    @Override
    public String getName() {
	return "epic";
    }

    @Override
    public float getToughness() {
	return 2;
    }

}
