package com.robertx22.blocks.gear_factory_station;

import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.mmorpg.Ref;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class StartupGearFactory {

    public static final String GEAR_FACTORY_ID = Ref.MODID + ":gear_factory_station";

    @ObjectHolder(GEAR_FACTORY_ID)
    public static TileEntityType<?> GEAR_FACTORY;

    public static Block BLOCK_GEAR_FACTORY;
    public static ItemBlock ITEMBLOCK_GEAR_FACTORY;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        BLOCK_GEAR_FACTORY = new BlockGearFactory();
        BLOCK_GEAR_FACTORY.setRegistryName(GEAR_FACTORY_ID);

        event.getRegistry().register(BLOCK_GEAR_FACTORY);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ITEMBLOCK_GEAR_FACTORY = new ItemBlock(BLOCK_GEAR_FACTORY, new Item.Properties().group(CreativeTabs.MyModTab));
        ITEMBLOCK_GEAR_FACTORY.setRegistryName(GEAR_FACTORY_ID);

        event.getRegistry().register(ITEMBLOCK_GEAR_FACTORY);
    }

}
