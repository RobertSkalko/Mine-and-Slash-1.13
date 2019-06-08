package com.robertx22.items.gearitems.bases.armor_materials;

import com.robertx22.mmorpg.Ref;
import net.minecraft.inventory.EquipmentSlotType;

public class MythicalMat extends BaseMat {

    static int[] damageReductions = new int[]{3, 6, 8, 3};

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return 1500;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return damageReductions[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public String getName() {
        return Ref.MODID + ":" + "mythical";
    }

    @Override
    public float getToughness() {
        return 2;
    }

}
