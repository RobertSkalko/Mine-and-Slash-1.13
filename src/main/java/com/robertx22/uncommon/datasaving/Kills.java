package com.robertx22.uncommon.datasaving;

import com.robertx22.saveclasses.PlayerMapKillsData;
import com.robertx22.uncommon.datasaving.base.LoadSave;
import net.minecraft.nbt.NBTTagCompound;

public class Kills {

    private static final String LOC = "kils_object";

    public static PlayerMapKillsData Load(NBTTagCompound nbt) {

        if (nbt == null) {
            return null;
        }
       
        return LoadSave.Load(new PlayerMapKillsData(), nbt, LOC);

    }

    public static void Save(NBTTagCompound nbt, PlayerMapKillsData gear) {

        if (nbt == null) {
            return;
        }

        if (gear != null) {
            LoadSave.Save(gear, nbt, LOC);
        }

    }
}
