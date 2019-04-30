package com.robertx22.uncommon.datasaving;

import com.robertx22.saveclasses.MapDataList;
import com.robertx22.uncommon.datasaving.base.LoadSave;
import net.minecraft.nbt.NBTTagCompound;

public class MapsNbt {

    private static final String LOC = "map_data_list";

    public static MapDataList Load(NBTTagCompound nbt) {

        if (nbt == null) {
            return null;
        }

        return LoadSave.Load(MapDataList.class, new MapDataList(), nbt, LOC);

    }

    public static void Save(NBTTagCompound nbt, MapDataList gear) {

        if (nbt == null) {
            return;
        }

        if (gear != null) {
            LoadSave.Save(gear, nbt, LOC);
        }

    }
}
