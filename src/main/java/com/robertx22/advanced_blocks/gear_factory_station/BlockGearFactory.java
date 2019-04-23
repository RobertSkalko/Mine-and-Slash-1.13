package com.robertx22.advanced_blocks.gear_factory_station;

import com.robertx22.advanced_blocks.BaseInventoryBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class BlockGearFactory extends BaseInventoryBlock {

    public BlockGearFactory() {
	super(Properties.create(Material.ROCK).hardnessAndResistance(5F));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Block> event) {
	event.getRegistry().register(new BlockGearFactory());

    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
	return new TileGearFactory();
    }

}