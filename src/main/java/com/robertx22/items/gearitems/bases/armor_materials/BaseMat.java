package com.robertx22.items.gearitems.bases.armor_materials;

import net.minecraft.util.SoundEvents;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;

public abstract class BaseMat implements IArmorMaterial {

    @Override
    public SoundEvent getSoundEvent() {
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return null;
    }

}
