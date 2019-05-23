package com.robertx22.uncommon.utilityclasses;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerUtils {

    public static NBTTagCompound getPersistentNBT(EntityPlayer player) {

        NBTTagCompound nbt = null;

        try {

            INBTBase basenbt = player.getEntityData().get(EntityPlayer.PERSISTED_NBT_TAG);

            if (basenbt != null) {
                nbt = (NBTTagCompound) basenbt;
            }
            if (nbt == null) {
                nbt = new NBTTagCompound();
            }

        } catch (Exception e) {
            nbt = new NBTTagCompound();
            e.printStackTrace();
        }

        return nbt;

    }

    public static void setPestistentNBT(EntityPlayer player, NBTTagCompound nbt) {

        player.getEntityData().put(EntityPlayer.PERSISTED_NBT_TAG, nbt);
    }

}
