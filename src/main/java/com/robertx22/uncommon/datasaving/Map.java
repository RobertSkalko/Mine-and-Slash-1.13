package com.robertx22.uncommon.datasaving;

import com.robertx22.saveclasses.MapItemData;

import info.loenwind.autosave.Reader;
import info.loenwind.autosave.Writer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Map {
    private static final String LOC = "MAP_ITEM_DATA";

    public static MapItemData Load(ItemStack stack) {
	if (stack == null) {
	    return null;
	}
	if (!stack.hasTag()) {
	    return null;
	}
	MapItemData data = null;
	if (stack.getTag().contains(LOC)) {
	    NBTTagCompound nbt = (NBTTagCompound) stack.getTag().get(LOC);
	    data = new MapItemData();
	    Reader.read(nbt, data);
	}

	return data;

    }

    public static void Save(ItemStack stack, MapItemData gear) {
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
	    new_nbt.put(LOC, object_nbt);
	    stack.setTag(new_nbt);

	}
    }
}
