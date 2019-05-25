package com.robertx22.dimensions.blocks;

import com.robertx22.dimensions.MapManager;
import com.robertx22.dimensions.MyTeleporter;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.MapWorldPlayerListData;
import com.robertx22.uncommon.SLOC;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class MapPortalBlock extends BlockEndPortal {

    public MapPortalBlock() {

        super(Block.Properties.create(Material.PORTAL)
                .hardnessAndResistance(100F)
                .doesNotBlockMovement());
        this.setRegistryName(new ResourceLocation(Ref.MODID, "map_portal_block"));

    }

    @Override
    public void onEntityCollision(IBlockState state, World world, BlockPos pos,
                                  Entity entity) {
        try {
            if (world.isRemote == false && entity instanceof EntityPlayer) {
                if (!entity.isBeingRidden() && entity.isNonBoss()) {

                    TileEntity en = world.getTileEntity(pos);

                    if (en instanceof TileMapPortal) {
                        TileMapPortal portal = (TileMapPortal) en;

                        portal.ontick();

                        if (portal.readyToTeleport()) {

                            ResourceLocation loc = DimensionType.getKey(entity.world.getDimension()
                                    .getType());

                            // prevents infinite teleport loop xD makes sure you dont teleport to the same
                            // dimension, forever
                            if (portal.id != loc.toString()) {

                                World mapworld = MapManager.getWorld(portal.id);

                                IWorldData data = Load.World(mapworld);

                                if (data == null) {
                                    entity.sendMessage(SLOC.chat("world_doesnt_exist"));
                                    world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);

                                } else if (data.isMapWorld() == false) { // TODO

                                    MapWorldPlayerListData worlddata = data.getWorldData();

                                    entity.sendMessage(SLOC.chat("traveling_to_mapworld")
                                            .appendText(portal.id + ""));

                                    BlockPos pos1 = world.getSpawnPoint();

                                    DimensionType type = mapworld.getDimension()
                                            .getType();

                                    EntityPlayer player = (EntityPlayer) entity;

                                    entity.changeDimension(type, new MyTeleporter(world, pos1, player));

                                } else { // if not mapworld
                                    entity.sendMessage(SLOC.chat("not_mapworld"));

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
