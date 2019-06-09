package com.robertx22.uncommon.utilityclasses;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockRayTraceResult;

import javax.annotation.Nullable;
import java.util.List;

public class PlayerUtils {

    @Nullable
    public static TileEntity getTileEntityLookedAt(PlayerEntity player) {
        BlockRayTraceResult ray = (BlockRayTraceResult) player.func_213324_a(20.0D, 0.0F, false);
        return player.world.getTileEntity(ray.getPos());
    }

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
