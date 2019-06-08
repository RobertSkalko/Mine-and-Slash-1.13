package com.robertx22.dimensions.blocks;

import com.robertx22.dimensions.MapManager;
import com.robertx22.dimensions.MyTeleporter;
import com.robertx22.uncommon.Chats;
import com.robertx22.uncommon.capability.PlayerMapData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class MapPortalBlock extends EndPortalBlock {

    public MapPortalBlock() {

        super(Block.Properties.create(Material.PORTAL, MaterialColor.BLACK)
                .doesNotBlockMovement()
                .lightValue(15)
                .hardnessAndResistance(2, 2));

        ;

    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos,
                                  Entity entity) {
        try {
            if (world.isRemote == false && entity instanceof PlayerEntity) {
                if (!entity.isBeingRidden() && entity.isNonBoss()) {

                    TileEntity en = world.getTileEntity(pos);

                    if (en instanceof TileMapPortal) {
                        TileMapPortal portal = (TileMapPortal) en;

                        portal.ontick();

                        if (portal.readyToTeleport()) {

                            ResourceLocation loc = MapManager.getResourceLocation(entity.world
                                    .getDimension()
                                    .getType());

                            // prevents infinite teleport loop xD makes sure you dont teleport to the same
                            // dimension, forever
                            if (portal.id != loc.toString()) {

                                World mapworld = MapManager.getWorld(portal.id);

                                if (WorldUtils.isMapWorld(mapworld)) {

                                    PlayerEntity player = (PlayerEntity) entity;

                                    PlayerMapData.IPlayerMapData data = Load.playerMapData(player);

                                    if (data.hasTimeForMap()) {
                                        entity.sendMessage(Chats.Teleport_started.locName());

                                        BlockPos pos1 = WorldUtils.getPosByLevel(mapworld, data
                                                .getLevel());

                                        DimensionType type = mapworld.getDimension()
                                                .getType();

                                        entity.changeDimension(type, new MyTeleporter(world, pos1, player));
                                    } else {
                                        entity.sendMessage(Chats.Not_enough_time.locName());
                                    }

                                } else { // if not mapworld

                                    world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);

                                }
                            }

                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new TileMapPortal();
    }

}
