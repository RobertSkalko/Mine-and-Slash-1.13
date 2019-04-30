package com.robertx22.uncommon.datasaving;

import com.robertx22.saveclasses.MapWorldPlayerListData;
import com.robertx22.uncommon.datasaving.base.LoadSave;
import net.minecraft.nbt.NBTTagCompound;

public class MapWorldPlayerList {
    private static final String LOC = "MAP_WORLD_OBJ";

    public static MapWorldPlayerListData Load(NBTTagCompound nbt) {

        if (nbt == null) {
            return null;
        }

        return LoadSave.Load(MapWorldPlayerListData.class, new MapWorldPlayerListData(), nbt, LOC);

    }

    public static void Save(NBTTagCompound nbt, MapWorldPlayerListData gear) {

        if (nbt == null) {
            return;
        }

        if (gear != null) {
            LoadSave.Save(gear, nbt, LOC);
        }

    }
}
