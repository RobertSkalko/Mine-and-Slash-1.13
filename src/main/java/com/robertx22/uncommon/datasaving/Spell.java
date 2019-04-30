package com.robertx22.uncommon.datasaving;

import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.uncommon.datasaving.base.LoadSave;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Spell {

    private static final String LOC = "SPELL_ITEM_DATA";

    public static SpellItemData Load(ItemStack stack) {

        if (stack == null) {
            return null;
        }
        if (!stack.hasTag()) {
            return null;
        }

        return LoadSave.Load(new SpellItemData(), stack.getTag(), LOC);

    }

    public static void Save(ItemStack stack, SpellItemData gear) {

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
