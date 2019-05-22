package com.robertx22.mmorpg.registers.common;

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

        r.register(TileEntityType.register(Ref.MODID + ":map_portal_tile", TileEntityType.Builder
                .create(TileMapPortal::new)));

        r.register(TileEntityType.register(BlockRegister.GEAR_REPAIR_ID, TileEntityType.Builder
                .create(TileInventoryRepair::new)));
        r.register(TileEntityType.register(BlockRegister.GEAR_MODIFY_ID, TileEntityType.Builder
                .create(TileInventoryModify::new)));
        r.register(TileEntityType.register(BlockRegister.GEAR_SALVAGE_ID, TileEntityType.Builder
                .create(TileInventorySalvage::new)));
        r.register(TileEntityType.register(BlockRegister.MAP_DEVICE_ID, TileEntityType.Builder
                .create(TileMap::new)));
        r.register(TileEntityType.register(BlockRegister.GEAR_FACTORY_ID, TileEntityType.Builder
                .create(TileGearFactory::new)));

    }

}
