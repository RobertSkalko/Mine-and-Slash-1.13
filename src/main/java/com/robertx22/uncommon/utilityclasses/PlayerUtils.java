package com.robertx22.uncommon.utilityclasses;

import com.robertx22.dimensions.MapManager;
import com.robertx22.mmorpg.MMORPG;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.play.server.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.storage.WorldInfo;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.Optional;

public class PlayerUtils {

    public static void sendPlayersMSGofStructureSpawnTEST(BlockPos pos, String name) {

        if (MMORPG.RUN_DEV_TOOLS) {
            for (ServerPlayerEntity player : MapManager.getServer()
                    .func_184103_al()
                    .getPlayers()) {

                player.sendMessage(new StringTextComponent(name + " Structure spawned at : " + pos
                        .toString()));

            }
        }
    }

    @Nullable
    public static Entity changeDimension(ServerPlayerEntity player,
                                         DimensionType destination, BlockPos pos) {

        if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(player, destination))
            return null;

        DimensionType dimensiontype = player.dimension;

        ServerWorld serverworld = player.server.getWorld(dimensiontype);
        player.dimension = destination;
        ServerWorld serverworld1 = player.server.getWorld(destination);
        WorldInfo worldinfo = player.world.getWorldInfo();
        player.connection.sendPacket(new SRespawnPacket(destination, worldinfo.getGenerator(), player.interactionManager
                .getGameType()));
        player.connection.sendPacket(new SServerDifficultyPacket(worldinfo.getDifficulty(), worldinfo
                .isDifficultyLocked()));
        PlayerList playerlist = player.server.func_184103_al();
        playerlist.updatePermissionLevel(player);
        serverworld.removeEntity(player, true); //Forge: the player entity is moved to the new world, NOT cloned. So keep the data alive with no matching invalidate call.
        player.revive();
        double d0 = player.posX;
        double d1 = player.posY;
        double d2 = player.posZ;
        float f = player.rotationPitch;
        float f1 = player.rotationYaw;

        double moveFactor = serverworld.getDimension()
                .getMovementFactor() / serverworld1.getDimension().getMovementFactor();
        d0 *= moveFactor;
        d2 *= moveFactor;

        player.setLocationAndAngles(d0, d1, d2, f1, f);
        player.setMotion(Vec3d.ZERO);

        double d7 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder().minX() + 16.0D);
        double d4 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder().minZ() + 16.0D);
        double d5 = Math.min(2.9999872E7D, serverworld1.getWorldBorder().maxX() - 16.0D);
        double d6 = Math.min(2.9999872E7D, serverworld1.getWorldBorder().maxZ() - 16.0D);
        d0 = MathHelper.clamp(d0, d7, d5);
        d2 = MathHelper.clamp(d2, d4, d6);
        player.setLocationAndAngles(d0, d1, d2, f1, f);
        player.setMotion(Vec3d.ZERO);

        player.setWorld(serverworld1);
        serverworld1.func_217447_b(player);
        player.func_213846_b(serverworld);
        player.connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), f1, f);
        player.setMotion(Vec3d.ZERO);

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

    public static PlayerEntity nearestPlayer(ServerWorld world, LivingEntity entity) {

        Optional<ServerPlayerEntity> player = world.getPlayers()
                .stream()
                .min(Comparator.comparingDouble(entity::getDistanceSq));

        if (player.isPresent()) {
            return player.get();
        }

        return null;

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
