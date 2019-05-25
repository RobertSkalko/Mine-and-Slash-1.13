package com.robertx22.dimensions.blocks;

import com.robertx22.dimensions.MapManager;
import com.robertx22.dimensions.MyTeleporter;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.SLOC;
import com.robertx22.uncommon.capability.PlayerMapData;
import com.robertx22.uncommon.capability.WorldUtils;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
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

        super(Block.Properties.create(Material.PORTAL, MaterialColor.BLACK)
                .doesNotBlockMovement()
                .lightValue(15)
                .hardnessAndResistance(-1.0F, 3600000.0F));

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

                            ResourceLocation loc = MapManager.getResourceLocation(entity.world
                                    .getDimension()
                                    .getType());

                            // prevents infinite teleport loop xD makes sure you dont teleport to the same
                            // dimension, forever
                            if (portal.id != loc.toString()) {

                                World mapworld = MapManager.getWorld(portal.id);

                                if (WorldUtils.isMapWorld(mapworld)) {

                                    EntityPlayer player = (EntityPlayer) entity;

                                    entity.sendMessage(SLOC.chat("traveling_to_mapworld")
                                            .appendText(portal.id + ""));

                                    PlayerMapData.IPlayerMapData data = Load.playerMapData(player);

                                    BlockPos pos1 = WorldUtils.getPosByLevel(mapworld, data
                                            .getLevel());

                                    DimensionType type = mapworld.getDimension()
                                            .getType();

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
