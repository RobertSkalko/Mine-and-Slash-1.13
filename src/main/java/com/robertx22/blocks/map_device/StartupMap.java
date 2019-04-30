package com.robertx22.blocks.map_device;

import com.robertx22.mmorpg.Ref;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ObjectHolder;

public class StartupMap {

    public static final String ID = Ref.MODID + ":map_device";

    @ObjectHolder(ID)
    public static TileEntityType<?> MAP_DEVICE;

    public static Block blockInventoryAdvanced;
    public static ItemBlock itemBlockInventoryAdvanced;

    public static void preInitCommon() {

        TileEntityType.register(ID + "_entity", TileEntityType.Builder.create(TileMap::new));

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        blockInventoryAdvanced = new BlockMap();
        blockInventoryAdvanced.setRegistryName(ID);

        event.getRegistry().register(blockInventoryAdvanced);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        itemBlockInventoryAdvanced = new ItemBlock(blockInventoryAdvanced, new Item.Properties());
        itemBlockInventoryAdvanced.setRegistryName(ID);

        event.getRegistry().register(itemBlockInventoryAdvanced);
    }

}