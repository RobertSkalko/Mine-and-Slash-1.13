package com.robertx22.items.gearitems.bases.armor_materials;

import com.robertx22.mmorpg.Ref;
import net.minecraft.inventory.EntityEquipmentSlot;

public class LegendaryMat extends BaseMat {

    static int[] damageReductions = new int[]{3, 5, 7, 3};

    @Override
    public int getDurability(EntityEquipmentSlot slotIn) {
        return 1250;
    }

    @Override
    public int getDamageReductionAmount(EntityEquipmentSlot slotIn) {
        return damageReductions[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public String getName() {
        return Ref.MODID + ":" + "legendary";
    }

    @Override
    public float getToughness() {
        return 2;
    }

}
