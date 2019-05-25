package com.robertx22.dimensions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.dimension.DimensionType;

public class MyTeleporter extends Teleporter {
    private final BlockPos targetPos;
    DimensionType type = null;

    public MyTeleporter(World world, BlockPos pos, EntityPlayer player) {
        super((WorldServer) world);
        this.targetPos = pos;
    }

    public MyTeleporter(World world, DimensionType type, BlockPos pos,
                        EntityPlayer player) {
        super((WorldServer) world);
        this.targetPos = pos;
        this.type = type;
    }

    @Override
    public void placeEntity(World world, Entity entity, float yaw) {

        entity.moveToBlockPosAndAngles(targetPos, yaw, entity.rotationPitch);

    }

}