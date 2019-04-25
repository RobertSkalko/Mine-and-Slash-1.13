package com.robertx22.dimensions.blocks;

import com.robertx22.mmorpg.Ref;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber
public class MapPortalBlock extends BlockEndPortal {

    @ObjectHolder(Ref.MODID + ":map_portal_block")
    public static Block BLOCK = null;

    public MapPortalBlock() {

	super(Block.Properties.create(Material.PORTAL).hardnessAndResistance(100F));
	this.setRegistryName(new ResourceLocation(Ref.MODID, "map_portal_block"));

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
	event.getRegistry().register(new MapPortalBlock());

    }

    @Override
    public void onEntityCollision(IBlockState state, World world, BlockPos pos, Entity entity) {
	try {
	    if (world.isRemote == false && entity instanceof EntityPlayer) {
		if (entity.isNonBoss()) {

		    TileEntity en = world.getTileEntity(pos);

		    if (en instanceof TileMapPortal) {
			TileMapPortal portal = (TileMapPortal) en;

			portal.ontick();

			/*
			 * if (portal.readyToTeleport()) {
			 * 
			 * // prevents infinite teleport loop xD makes sure you dont teleport to the
			 * same // dimension, forever if (portal.id != entity.dimension) {
			 * 
			 * WorldServer worldserver =
			 * FMLCommonHandler.instance().getMinecraftServerInstance()
			 * .getWorld(portal.id); // loads the world apparently
			 * 
			 * IWorldData data = Load.World(DimensionManager.getWorld(portal.id));
			 * 
			 * if (data == null) { entity.sendMessage(SLOC.chat("world_doesnt_exist"));
			 * world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
			 * 
			 * } else if (data.isSetForDelete()) {
			 * entity.sendMessage(SLOC.chat("world_is_closed")); world.setBlockState(pos,
			 * Blocks.AIR.getDefaultState(), 2);
			 * 
			 * } else if (data.isMapWorld()) {
			 * 
			 * MapWorldData worlddata = data.getWorldData();
			 * 
			 * if (worlddata.joinedPlayerIDs.size() < ModConfig.Server.MAX_PLAYERS_PER_MAP
			 * || worlddata.joinedPlayerIDs.contains(entity.getUniqueID().toString())) {
			 * 
			 * if (worlddata.joinedPlayerIDs .contains(entity.getUniqueID().toString()) ==
			 * false) { worlddata.joinedPlayerIDs.add(entity.getUniqueID().toString());
			 * data.setWorldData(worlddata); }
			 * 
			 * entity.sendMessage( SLOC.chat("traveling_to_mapworld").appendText(portal.id +
			 * ""));
			 * 
			 * World w = DimensionManager.getWorld(portal.id);
			 * 
			 * BlockPos pos1 = w.getSpawnPoint(); BlockPos pos2 =
			 * w.provider.getRandomizedSpawnPoint();
			 * 
			 * entity.changeDimension(portal.id, new MyTeleporter(world, pos2,
			 * (EntityPlayer) entity, DimensionManager.portal.id));
			 * 
			 * }
			 * 
			 * if (worlddata.joinedPlayerIDs.size() > ModConfig.Server.MAX_PLAYERS_PER_MAP)
			 * { entity.sendMessage(SLOC.chat("mapworld_max_capacity"));
			 * 
			 * } } else { // if not mapworld entity.sendMessage(SLOC.chat("not_mapworld"));
			 * 
			 * world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
			 * 
			 * } }
			 * 
			 * }
			 */
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
