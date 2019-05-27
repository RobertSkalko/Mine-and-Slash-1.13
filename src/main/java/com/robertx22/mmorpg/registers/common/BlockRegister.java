package com.robertx22.mmorpg.registers.common;

import com.robertx22.blocks.egg_loot_crate.EggLootCrateBlock;
import com.robertx22.blocks.gear_factory_station.BlockGearFactory;
import com.robertx22.blocks.item_modify_station.BlockInventoryModify;
import com.robertx22.blocks.map_device.BlockMap;
import com.robertx22.blocks.repair_station.BlockInventoryRepair;
import com.robertx22.blocks.salvage_station.BlockInventorySalvage;
import com.robertx22.blocks.simple.AttunementBlock;
import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.dimensions.blocks.MapPortalBlock;
import com.robertx22.items.ores.ItemOre;
import com.robertx22.mmorpg.Ref;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegister {

    public static final String GEAR_FACTORY_ID = Ref.MODID + ":gear_factory_station";
    public static final String GEAR_MODIFY_ID = Ref.MODID + ":modify_station";
    public static final String MAP_DEVICE_ID = Ref.MODID + ":map_device";
    public static final String GEAR_SALVAGE_ID = Ref.MODID + ":salvage_station";
    public static final String GEAR_REPAIR_ID = Ref.MODID + ":repair_station";
    public static final String ATTUNEMENT_ALTAR_ID = Ref.MODID + ":attunement_altar";
    public static final String EGG_LOOT_CRATE_ID = Ref.MODID + ":egg_loot_crate";
    public static final String MAP_PORTAL_BLOCK_ID = Ref.MODID + ":map_portal_block";

    // NEW BLOCK
    @ObjectHolder(EGG_LOOT_CRATE_ID)
    public static TileEntityType<?> EGG_LOOT_CRATE;
    @ObjectHolder(EGG_LOOT_CRATE_ID)
    public static Block EGG_LOOT_CRATE_BLOCK;
    //NEW BLOCK
    @ObjectHolder(MAP_PORTAL_BLOCK_ID)
    public static Block PORTAL_BLOCK;
    @ObjectHolder(MAP_PORTAL_BLOCK_ID)
    public static TileEntityType<?> TILE_PORTAL_BLOCK;
    // NEW BLOCK
    @ObjectHolder(ATTUNEMENT_ALTAR_ID)
    public static Block ATTUNEMENT_ALTAR_BLOCK;
    @ObjectHolder(ATTUNEMENT_ALTAR_ID)
    public static ItemBlock ATTUNEMENT_ALTAR;
    // NEW BLOCK
    @ObjectHolder(GEAR_FACTORY_ID)
    public static TileEntityType<?> GEAR_FACTORY;
    @ObjectHolder(GEAR_FACTORY_ID)
    public static Block BLOCK_GEAR_FACTORY;
    @ObjectHolder(GEAR_FACTORY_ID)
    public static ItemBlock ITEMBLOCK_GEAR_FACTORY;
    // NEW BLOCK
    @ObjectHolder(GEAR_MODIFY_ID)
    public static TileEntityType<?> GEAR_MODIFY;
    @ObjectHolder(GEAR_MODIFY_ID)
    public static Block BLOCK_GEAR_MODIFY;
    @ObjectHolder(GEAR_MODIFY_ID)
    public static ItemBlock ITEMBLOCK_GEAR_MODIFY;
    // NEW BLOCK
    @ObjectHolder(GEAR_REPAIR_ID)
    public static TileEntityType<?> GEAR_REPAIR;
    @ObjectHolder(GEAR_REPAIR_ID)
    public static Block BLOCK_GEAR_REPAIR;
    @ObjectHolder(GEAR_REPAIR_ID)
    public static ItemBlock ITEMBLOCK_GEAR_REPAIR;
    // NEW BLOCK
    @ObjectHolder(GEAR_SALVAGE_ID)
    public static TileEntityType<?> GEAR_SALVAGE;
    @ObjectHolder(GEAR_SALVAGE_ID)
    public static Block BLOCK_GEAR_SALVAGE;
    @ObjectHolder(GEAR_SALVAGE_ID)
    public static ItemBlock ITEMBLOCK_GEAR_SALVAGE;
    // NEW BLOCK
    @ObjectHolder(MAP_DEVICE_ID)
    public static TileEntityType<?> MAP_DEVICE;
    @ObjectHolder(MAP_DEVICE_ID)
    public static Block BLOCK_MAP_DEVICE;
    @ObjectHolder(MAP_DEVICE_ID)
    public static ItemBlock ITEMBLOCK_MAP_DEVICE;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> reg = event.getRegistry();

        reg.register(new BlockGearFactory().setRegistryName(GEAR_FACTORY_ID));
        reg.register(new BlockMap().setRegistryName(MAP_DEVICE_ID));
        reg.register(new BlockInventorySalvage().setRegistryName(GEAR_SALVAGE_ID));
        reg.register(new BlockInventoryRepair().setRegistryName(GEAR_REPAIR_ID));
        reg.register(new BlockInventoryModify().setRegistryName(GEAR_MODIFY_ID));
        reg.register(new AttunementBlock().setRegistryName(ATTUNEMENT_ALTAR_ID));
        reg.register(new MapPortalBlock().setRegistryName(MAP_PORTAL_BLOCK_ID));
        reg.register(new EggLootCrateBlock().setRegistryName(EGG_LOOT_CRATE_ID));

        ItemOre.RegisterBlocks(event);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> reg = event.getRegistry();
        Item.Properties stationProp = new Item.Properties().group(CreativeTabs.MyModTab);

        reg.register(new ItemBlock(BLOCK_GEAR_FACTORY, stationProp).setRegistryName(GEAR_FACTORY_ID));
        reg.register(new ItemBlock(BLOCK_GEAR_REPAIR, stationProp).setRegistryName(GEAR_REPAIR_ID));
        reg.register(new ItemBlock(BLOCK_GEAR_MODIFY, stationProp).setRegistryName(GEAR_MODIFY_ID));
        reg.register(new ItemBlock(BLOCK_MAP_DEVICE, stationProp).setRegistryName(MAP_DEVICE_ID));
        reg.register(new ItemBlock(BLOCK_GEAR_SALVAGE, stationProp).setRegistryName(GEAR_SALVAGE_ID));
        reg.register(new ItemBlock(ATTUNEMENT_ALTAR_BLOCK, stationProp).setRegistryName(ATTUNEMENT_ALTAR_ID));
        reg.register(new ItemBlock(EGG_LOOT_CRATE_BLOCK, stationProp).setRegistryName(EGG_LOOT_CRATE_ID));

        ItemOre.RegisterItems(event);
    }
}
