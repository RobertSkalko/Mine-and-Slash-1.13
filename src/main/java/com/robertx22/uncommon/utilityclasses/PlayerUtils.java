package com.robertx22.uncommon.utilityclasses;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerUtils {

    private static String MY_MOD_TAG = "mmorpg.mine_and_slash";

    public static NBTTagCompound getPersistentNBT(EntityPlayer player) {

        NBTTagCompound nbt = null;

        try {

            nbt = (NBTTagCompound) player.getEntityData()
                    .get(EntityPlayer.PERSISTED_NBT_TAG);

            if (nbt.contains(MY_MOD_TAG)) {
                nbt = (NBTTagCompound) nbt.get(MY_MOD_TAG);
            }

        } catch (Exception e) {

            nbt = new NBTTagCompound();

        }

        return nbt;

    }

    public static void setPestistentNBT(EntityPlayer player, NBTTagCompound nbt) {
        player.getEntityData().put(EntityPlayer.PERSISTED_NBT_TAG, nbt);
    }

}
