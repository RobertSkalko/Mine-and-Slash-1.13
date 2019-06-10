package com.robertx22.uncommon.utilityclasses;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap;

import javax.annotation.Nullable;
import java.util.List;

public class PlayerUtils {

    @Nullable
    public static Entity changeDimension(PlayerEntity player, DimensionType destination) {
        if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(player, destination))
            return null;
        if (!player.world.isRemote && !player.removed) {
            player.world.getProfiler().startSection("changeDimension");
            MinecraftServer minecraftserver = player.getServer();
            DimensionType dimensiontype = player.dimension;
            ServerWorld serverworld = minecraftserver.getWorld(dimensiontype);
            ServerWorld serverworld1 = minecraftserver.getWorld(destination);
            player.dimension = destination;
            player.detach();
            player.world.getProfiler().startSection("reposition");
            Vec3d vec3d = player.getMotion();
            float f = 0.0F;
            BlockPos blockpos;
            if (dimensiontype == DimensionType.THE_END && destination == DimensionType.OVERWORLD) {
                blockpos = serverworld1.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, serverworld1
                        .getSpawnPoint());
            } else if (destination == DimensionType.THE_END) {
                blockpos = serverworld1.getSpawnCoordinate();
            } else {
                double movementFactor = serverworld.getDimension()
                        .getMovementFactor() / serverworld1.getDimension()
                        .getMovementFactor();
                double d0 = player.posX * movementFactor;
                double d1 = player.posZ * movementFactor;
                double d3 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder()
                        .minX() + 16.0D);
                double d4 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder()
                        .minZ() + 16.0D);
                double d5 = Math.min(2.9999872E7D, serverworld1.getWorldBorder()
                        .maxX() - 16.0D);
                double d6 = Math.min(2.9999872E7D, serverworld1.getWorldBorder()
                        .maxZ() - 16.0D);
                d0 = MathHelper.clamp(d0, d3, d5);
                d1 = MathHelper.clamp(d1, d4, d6);

                blockpos = new BlockPos(d0, player.posY, d1);

            }

            player.world.getProfiler().endStartSection("reloading");
            Entity entity = player.getType().create(serverworld1);
            if (entity != null) {
                entity.copyDataFromOld(player);
                entity.moveToBlockPosAndAngles(blockpos, entity.rotationYaw + f, entity.rotationPitch);
                entity.setMotion(vec3d);
                serverworld1.func_217460_e(entity);
            }

            player.world.getProfiler().endSection();
            serverworld.resetUpdateEntityTick();
            serverworld1.resetUpdateEntityTick();
            player.world.getProfiler().endSection();

            return entity;
        } else {
            return null;
        }
    }

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
