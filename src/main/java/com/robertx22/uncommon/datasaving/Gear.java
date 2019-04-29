package com.robertx22.uncommon.datasaving;

import com.robertx22.saveclasses.GearItemData;
import info.loenwind.autosave.Reader;
import info.loenwind.autosave.Writer;
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

        GearItemData data = null;
        if (stack.getTag().hasKey(LOC)) {
            NBTTagCompound nbt = (NBTTagCompound) stack.getTag().getTag(LOC);
            data = new GearItemData();
            Reader.read(nbt, data);
        }

        return data;

    }

    public static void Save(ItemStack stack, GearItemData gear) {

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
            new_nbt.setInt("rarity", gear.Rarity);
            stack.setTag(new_nbt);

        }

    }

}
