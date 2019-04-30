package com.robertx22.uncommon.datasaving;

import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.base.LoadSave;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Gear {

    private static final String LOC = "GEAR_ITEM_DATA";

    public static GearItemData Load(ItemStack stack) {

        if (stack == null) {
            return null;
        }
        if (!stack.hasTag()) {
            return null;
        }

        return LoadSave.Load(new GearItemData(), stack.getTag(), LOC);

    }

    public static void Save(ItemStack stack, GearItemData gear) {

        if (stack == null) {
            return;
        }
        if (!stack.hasTag()) {
            stack.setTag(new NBTTagCompound());
        }
        if (gear != null) {
            LoadSave.Save(gear, stack.getTag(), LOC);
        }

    }

}
