package com.robertx22.uncommon.datasaving;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.base.LoadSave;
import net.minecraft.nbt.NBTTagCompound;

public class UnitNbt {
    private static final String LOC = "unit_object";

    public static Unit Load(NBTTagCompound nbt) {

        if (nbt == null) {
            return null;
        }

        return LoadSave.Load(Unit.class, new Unit(), nbt, LOC);

    }

    public static void Save(NBTTagCompound nbt, Unit gear) {

        if (nbt == null) {
            return;
        }

        if (gear != null) {
            LoadSave.Save(gear, nbt, LOC);
        }

    }
}
