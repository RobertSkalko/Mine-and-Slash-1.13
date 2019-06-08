package com.robertx22.mmorpg.registers.common;

import com.robertx22.blocks.egg_loot_crate.EggLootCrateTileEntity;
import com.robertx22.blocks.gear_factory_station.TileGearFactory;
import com.robertx22.blocks.item_modify_station.TileInventoryModify;
import com.robertx22.blocks.map_device.TileMap;
import com.robertx22.blocks.repair_station.TileInventoryRepair;
import com.robertx22.blocks.salvage_station.TileInventorySalvage;
import com.robertx22.dimensions.blocks.TileMapPortal;
import com.robertx22.mmorpg.Ref;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntityRegister {

    @SubscribeEvent
    public static void onTileEntityRegistry(
            final RegistryEvent.Register<TileEntityType<?>> e) {

        IForgeRegistry<TileEntityType<?>> r = e.getRegistry();

        r.register(TileEntityType.Builder.func_223042_a(TileMapPortal::new, BlockRegister.PORTAL_BLOCK)
                .build(null)
                .setRegistryName(BlockRegister.MAP_PORTAL_BLOCK_ID));

        r.register(TileEntityType.Builder.func_223042_a(TileInventoryRepair::new, BlockRegister.BLOCK_GEAR_REPAIR)
                .build(null)
                .setRegistryName(BlockRegister.GEAR_REPAIR_ID));

        r.register(TileEntityType.Builder.func_223042_a(TileInventoryModify::new, BlockRegister.BLOCK_GEAR_MODIFY)
                .build(null)
                .setRegistryName(BlockRegister.GEAR_MODIFY_ID));

        r.register(TileEntityType.Builder.func_223042_a(TileInventorySalvage::new, BlockRegister.BLOCK_GEAR_SALVAGE)
                .build(null)
                .setRegistryName(BlockRegister.GEAR_SALVAGE_ID));

        r.register(TileEntityType.Builder.func_223042_a(TileMap::new, BlockRegister.BLOCK_MAP_DEVICE)
                .build(null)
                .setRegistryName(BlockRegister.MAP_DEVICE_ID));

        r.register(TileEntityType.Builder.func_223042_a(TileGearFactory::new, BlockRegister.BLOCK_GEAR_FACTORY)
                .build(null)
                .setRegistryName(BlockRegister.GEAR_FACTORY_ID));

        r.register(TileEntityType.Builder.func_223042_a(EggLootCrateTileEntity::new, BlockRegister.EGG_LOOT_CRATE_BLOCK)
                .build(null)
                .setRegistryName(BlockRegister.EGG_LOOT_CRATE_ID));

    }

}
