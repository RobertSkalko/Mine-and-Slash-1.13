package com.robertx22.uncommon.datasaving;

import com.robertx22.saveclasses.PlayerOncePerMapData;
import com.robertx22.uncommon.datasaving.base.LoadSave;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerOncePerMap {

    private static final String LOC = "mmorpg:PLAYER_ONCE_PER_MAP_DATA";

    public static PlayerOncePerMapData Load(NBTTagCompound nbt) {

        if (nbt == null) {
            return null;
        }

        return LoadSave.Load(PlayerOncePerMapData.class, new PlayerOncePerMapData(), nbt, LOC);

    }

    public static void Save(NBTTagCompound nbt, PlayerOncePerMapData gear) {

        if (nbt == null) {
            return;
        }

        if (gear != null) {
            LoadSave.Save(gear, nbt, LOC);
        }

    }
}
