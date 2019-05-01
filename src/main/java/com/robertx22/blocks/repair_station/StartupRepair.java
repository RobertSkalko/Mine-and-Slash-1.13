package com.robertx22.blocks.repair_station;

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
public class StartupRepair {
    public static final String ID = Ref.MODID + ":repair_station";

    @ObjectHolder(ID)
    public static TileEntityType<?> GEAR_REPAIR;

    public static Block blockInventoryAdvanced;
    public static ItemBlock itemBlockInventoryAdvanced;

    public static void preInitCommon() {

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        blockInventoryAdvanced = new BlockInventoryRepair();
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