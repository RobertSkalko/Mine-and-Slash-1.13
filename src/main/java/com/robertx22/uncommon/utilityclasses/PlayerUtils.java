package com.robertx22.uncommon.utilityclasses;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class PlayerUtils {

    public static EntityPlayer findNearest(Entity entity, float radius) {

        List<EntityPlayer> players = entity.world.getEntitiesWithinAABB(EntityPlayer.class, entity
                .getBoundingBox()
                .grow(radius));

        EntityPlayer nearest = null;

        for (EntityPlayer player : players) {

            if (nearest == null || nearest.getDistance(entity) > player.getDistance(entity)) {
                nearest = player;
            }

        }

        return nearest;
    }

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
