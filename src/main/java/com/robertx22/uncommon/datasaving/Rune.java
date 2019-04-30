package com.robertx22.uncommon.datasaving;

import com.robertx22.saveclasses.rune.RuneItemData;
import com.robertx22.uncommon.datasaving.base.LoadSave;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Rune {

    private static final String LOC = "RUNE_ITEM_DATA";

    public static RuneItemData Load(ItemStack stack) {

        if (stack == null) {
            return null;
        }
        if (!stack.hasTag()) {
            return null;
        }

        return LoadSave.Load(new RuneItemData(), stack.getTag(), LOC);

    }

    public static void Save(ItemStack stack, RuneItemData gear) {

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
