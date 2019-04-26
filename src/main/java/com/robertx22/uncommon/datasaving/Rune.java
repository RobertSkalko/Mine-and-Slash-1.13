package com.robertx22.uncommon.datasaving;

import com.robertx22.saveclasses.rune.RuneItemData;

import info.loenwind.autosave.Reader;
import info.loenwind.autosave.Writer;
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

	RuneItemData data = null;
	if (stack.getTag().hasKey(LOC)) {
	    NBTTagCompound nbt = (NBTTagCompound) stack.getTag().getTag(LOC);
	    data = new RuneItemData();
	    Reader.read(nbt, data);
	}

	return data;

    }

    public static void Save(ItemStack stack, RuneItemData gear) {

	if (stack == null) {
	    return;
	}
	if (!stack.hasTag()) {
	    stack.setTag(new NBTTagCompound());
	}

	if (gear != null) {
	    NBTTagCompound object_nbt = new NBTTagCompound();
	    Writer.write(object_nbt, gear);
	    NBTTagCompound new_nbt = stack.getTag();
	    new_nbt.setTag(LOC, object_nbt);
	    new_nbt.setInt("rarity", gear.rarity);
	    stack.setTag(new_nbt);

	}

    }
}
