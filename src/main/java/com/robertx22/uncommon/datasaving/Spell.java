package com.robertx22.uncommon.datasaving;

import com.robertx22.saveclasses.SpellItemData;

import info.loenwind.autosave.Reader;
import info.loenwind.autosave.Writer;
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

	SpellItemData data = null;
	if (stack.getTag().contains(LOC)) {
	    NBTTagCompound nbt = (NBTTagCompound) stack.getTag().get(LOC);
	    data = new SpellItemData();
	    Reader.read(nbt, data);
	}

	return data;

    }

    public static void Save(ItemStack stack, SpellItemData spell) {
	if (stack == null) {
	    return;
	}
	if (!stack.hasTag()) {
	    stack.setTag(new NBTTagCompound());
	}

	if (spell != null) {
	    NBTTagCompound object_nbt = new NBTTagCompound();
	    Writer.write(object_nbt, spell);
	    NBTTagCompound new_nbt = stack.getTag();
	    new_nbt.put(LOC, object_nbt);
	    new_nbt.putInt("rarity", spell.rarity);
	    stack.setTag(new_nbt);

	}
    }

}
