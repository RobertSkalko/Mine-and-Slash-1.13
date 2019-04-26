package com.robertx22.blocks.gear_factory_station;

import com.robertx22.mmorpg.Ref;

import net.minecraft.block.Block;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

public class StartupGearFactory {

    public static final String ID = Ref.MODID + ":gear_factory_station";

    @ObjectHolder(ID)
    public static TileEntityType<?> GEAR_FACTORY;

    public static Block blockInventoryAdvanced;
    public static ItemBlock itemBlockInventoryAdvanced;

    public static void preInitCommon() {
	blockInventoryAdvanced = new BlockGearFactory();
	blockInventoryAdvanced.setRegistryName(ID);
	ForgeRegistries.BLOCKS.register(blockInventoryAdvanced);

	itemBlockInventoryAdvanced = new ItemBlock(blockInventoryAdvanced, new Properties());
	itemBlockInventoryAdvanced.setRegistryName(blockInventoryAdvanced.getRegistryName());
	ForgeRegistries.ITEMS.register(itemBlockInventoryAdvanced);

	TileEntityType.register(ID + "_entity", TileEntityType.Builder.create(TileGearFactory::new));

    }

}
