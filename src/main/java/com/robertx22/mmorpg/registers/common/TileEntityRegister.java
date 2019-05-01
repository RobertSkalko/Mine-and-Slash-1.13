package com.robertx22.mmorpg.registers.common;

import com.robertx22.blocks.gear_factory_station.StartupGearFactory;
import com.robertx22.blocks.gear_factory_station.TileGearFactory;
import com.robertx22.blocks.item_modify_station.StartupModify;
import com.robertx22.blocks.item_modify_station.TileInventoryModify;
import com.robertx22.blocks.map_device.StartupMap;
import com.robertx22.blocks.map_device.TileMap;
import com.robertx22.blocks.repair_station.StartupRepair;
import com.robertx22.blocks.repair_station.TileInventoryRepair;
import com.robertx22.blocks.salvage_station.StartupSalvage;
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

        TileEntityType.register(Ref.MODID + ":map_portal_tile", TileEntityType.Builder.create(TileMapPortal::new));

        IForgeRegistry<TileEntityType<?>> r = e.getRegistry();

        r.register(TileEntityType.register(StartupRepair.ID, TileEntityType.Builder.create(TileInventoryRepair::new)));
        r.register(TileEntityType.register(StartupModify.ID, TileEntityType.Builder.create(TileInventoryModify::new)));
        r.register(TileEntityType.register(StartupSalvage.ID, TileEntityType.Builder.create(TileInventorySalvage::new)));
        r.register(TileEntityType.register(StartupMap.ID, TileEntityType.Builder.create(TileMap::new)));
        r.register(TileEntityType.register(StartupGearFactory.GEAR_FACTORY_ID, TileEntityType.Builder
                .create(TileGearFactory::new)));

    }

}
