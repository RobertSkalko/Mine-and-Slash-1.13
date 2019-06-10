package com.robertx22.uncommon.utilityclasses;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.play.server.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.management.PlayerList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.storage.WorldInfo;

import javax.annotation.Nullable;
import java.util.List;

public class PlayerUtils {

    @Nullable
    public static Entity changeDimension(ServerPlayerEntity player,
                                         DimensionType destination, BlockPos pos) {

        if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(player, destination))
            return null;
        player.setInvulnerable(true);
        DimensionType dimensiontype = player.dimension;
        if (dimensiontype == DimensionType.THE_END && destination == DimensionType.OVERWORLD) {
            player.detach();
            player.getServerWorld().func_217434_e(player);
            if (!player.queuedEndExit) {
                player.queuedEndExit = true;
                player.connection.sendPacket(new SChangeGameStatePacket(4, true ? 0.0F : 1.0F));

            }

            return player;
        } else {
            ServerWorld serverworld = player.server.getWorld(dimensiontype);
            player.dimension = destination;
            ServerWorld serverworld1 = player.server.getWorld(destination);
            WorldInfo worldinfo = player.world.getWorldInfo();
            player.connection.sendPacket(new SRespawnPacket(destination, worldinfo.getGenerator(), player.interactionManager
                    .getGameType()));
            player.connection.sendPacket(new SServerDifficultyPacket(worldinfo.getDifficulty(), worldinfo
                    .isDifficultyLocked()));
            PlayerList playerlist = player.server.getPlayerList();
            playerlist.updatePermissionLevel(player);
            serverworld.removeEntity(player, true); //Forge: the player entity is moved to the new world, NOT cloned. So keep the data alive with no matching invalidate call.
            player.revive();
            double d0 = player.posX;
            double d1 = player.posY;
            double d2 = player.posZ;
            float f = player.rotationPitch;
            float f1 = player.rotationYaw;
            double d3 = 8.0D;
            float f2 = f1;
            serverworld.getProfiler().startSection("moving");
            double moveFactor = serverworld.getDimension()
                    .getMovementFactor() / serverworld1.getDimension()
                    .getMovementFactor();
            d0 *= moveFactor;
            d2 *= moveFactor;
            if (dimensiontype == DimensionType.OVERWORLD && destination == DimensionType.NETHER) {
                player.enteredNetherPosition = new Vec3d(player.posX, player.posY, player.posZ);
            } else if (dimensiontype == DimensionType.OVERWORLD && destination == DimensionType.THE_END) {
                BlockPos blockpos = serverworld1.getSpawnCoordinate();
                d0 = (double) blockpos.getX();
                d1 = (double) blockpos.getY();
                d2 = (double) blockpos.getZ();
                f1 = 90.0F;
                f = 0.0F;
            }

            player.setLocationAndAngles(d0, d1, d2, f1, f);
            serverworld.getProfiler().endSection();
            serverworld.getProfiler().startSection("placing");
            double d7 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder()
                    .minX() + 16.0D);
            double d4 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder()
                    .minZ() + 16.0D);
            double d5 = Math.min(2.9999872E7D, serverworld1.getWorldBorder()
                    .maxX() - 16.0D);
            double d6 = Math.min(2.9999872E7D, serverworld1.getWorldBorder()
                    .maxZ() - 16.0D);
            d0 = MathHelper.clamp(d0, d7, d5);
            d2 = MathHelper.clamp(d2, d4, d6);
            player.setLocationAndAngles(d0, d1, d2, f1, f);
            if (destination == DimensionType.THE_END) {
                int i = MathHelper.floor(player.posX);
                int j = MathHelper.floor(player.posY) - 1;
                int k = MathHelper.floor(player.posZ);
                int l = 1;
                int i1 = 0;

                for (int j1 = -2; j1 <= 2; ++j1) {
                    for (int k1 = -2; k1 <= 2; ++k1) {
                        for (int l1 = -1; l1 < 3; ++l1) {
                            int i2 = i + k1 * 1 + j1 * 0;
                            int j2 = j + l1;
                            int k2 = k + k1 * 0 - j1 * 1;
                            boolean flag = l1 < 0;
                            serverworld1.setBlockState(new BlockPos(i2, j2, k2), flag ? Blocks.OBSIDIAN
                                    .getDefaultState() : Blocks.AIR.getDefaultState());
                        }
                    }
                }

                player.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), f1, 0.0F);
                player.setMotion(Vec3d.ZERO);
            }

            serverworld.getProfiler().endSection();
            player.setWorld(serverworld1);
            serverworld1.func_217447_b(player);
            player.func_213846_b(serverworld);
            player.connection.setPlayerLocation(player.posX, player.posY, player.posZ, f1, f);
            player.interactionManager.setWorld(serverworld1);
            player.connection.sendPacket(new SPlayerAbilitiesPacket(player.abilities));
            playerlist.sendWorldInfo(player, serverworld1);
            playerlist.sendInventory(player);

            for (EffectInstance effectinstance : player.getActivePotionEffects()) {
                player.connection.sendPacket(new SPlayEntityEffectPacket(player.getEntityId(), effectinstance));
            }

            player.connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));

            net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerChangedDimensionEvent(player, dimensiontype, destination);
            return player;
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
