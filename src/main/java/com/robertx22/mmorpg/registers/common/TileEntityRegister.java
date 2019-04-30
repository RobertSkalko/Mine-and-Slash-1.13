package com.robertx22.mmorpg.registers.common;

import com.robertx22.blocks.gear_factory_station.StartupGearFactory;
import com.robertx22.blocks.item_modify_station.StartupModify;
import com.robertx22.blocks.map_device.StartupMap;
import com.robertx22.blocks.repair_station.StartupRepair;
import com.robertx22.blocks.salvage_station.StartupSalvage;
import com.robertx22.dimensions.blocks.TileMapPortal;
import com.robertx22.mmorpg.Ref;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class TileEntityRegister {

    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> e) {
	TileEntityType.register(Ref.MODID + ":map_portal_tile", TileEntityType.Builder.create(TileMapPortal::new));

	StartupRepair.preInitCommon();
	StartupSalvage.preInitCommon();
	StartupModify.preInitCommon();
	StartupGearFactory.preInitCommon();
	StartupMap.preInitCommon();

    }

}
