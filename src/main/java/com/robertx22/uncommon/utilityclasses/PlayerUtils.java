package com.robertx22.uncommon.utilityclasses;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;

import java.util.List;

public class PlayerUtils {

    public static PlayerEntity findNearest(Entity entity, float radius) {

        List<PlayerEntity> players = entity.world.getEntitiesWithinAABB(PlayerEntity.class, entity
                .getBoundingBox()
                .grow(radius));

        PlayerEntity nearest = null;

        for (PlayerEntity player : players) {

            if (nearest == null || nearest.getDistance(entity) > player.getDistance(entity)) {
                nearest = player;
            }

        }

        return nearest;
    }

    public static CompoundNBT getPersistentNBT(PlayerEntity player) {

        CompoundNBT nbt = null;

        try {

            INBT basenbt = player.getEntityData().get(PlayerEntity.PERSISTED_NBT_TAG);

            if (basenbt != null) {
                nbt = (CompoundNBT) basenbt;
            }
            if (nbt == null) {
                nbt = new CompoundNBT();
            }

        } catch (Exception e) {
            nbt = new CompoundNBT();
            e.printStackTrace();
        }

        return nbt;

    }

    public static void setPestistentNBT(PlayerEntity player, CompoundNBT nbt) {

        player.getEntityData().put(PlayerEntity.PERSISTED_NBT_TAG, nbt);
    }

}
