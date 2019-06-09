package com.robertx22.uncommon.utilityclasses;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class PlayerUtils {

    // TODO find nearest, not any
    public static BlockPos getNearestTileEntity(World world, BlockPos pos) {

        for (int x = -5; x < 5; x++) {
            for (int y = -5; y < 5; y++) {
                for (int z = -5; z < 5; z++) {

                    BlockPos check = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);

                    if (world.getTileEntity(check) != null) {
                        return check;
                    }

                }
            }

        }

        return pos;

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
