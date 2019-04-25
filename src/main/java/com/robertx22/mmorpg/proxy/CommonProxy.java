package com.robertx22.mmorpg.proxy;

import com.google.common.graph.Network;
import com.robertx22.advanced_blocks.gear_factory_station.StartupGearFactory;
import com.robertx22.advanced_blocks.item_modify_station.StartupModify;
import com.robertx22.advanced_blocks.map_device.StartupMap;
import com.robertx22.advanced_blocks.repair_station.StartupRepair;
import com.robertx22.advanced_blocks.salvage_station.StartupSalvage;
import com.robertx22.customitems.ores.ItemOre;
import com.robertx22.dimensions.blocks.TileMapPortal;
import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.config.non_mine_items.Serialization;
import com.robertx22.mmorpg.gui.GuiHandlerAll;
import com.robertx22.mmorpg.registers.GearItemRegisters;
import com.robertx22.network.DmgNumPacket;
import com.robertx22.network.EntityUnitPackage;
import com.robertx22.network.MessagePackage;
import com.robertx22.network.PacketHandler;
import com.robertx22.network.ParticlePackage;
import com.robertx22.network.PlayerUnitPackage;
import com.robertx22.network.WorldPackage;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.PlayerDeathItems;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.unique_items.UniqueItemRegister;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;

public class CommonProxy implements IProxy {

    @Override
    public void preInit(FMLCommonSetupEvent event) {
	ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY,
		() -> GuiHandlerAll::getClientGuiElement);

	Serialization.generateConfig();

	UniqueItemRegister.registerAll();

	GameRegistry.registerTileEntity(TileMapPortal.class, new ResourceLocation(Ref.MODID, "map_portal_tile"));

	GearItemRegisters.register();

	ItemOre.Register();
	StartupRepair.preInitCommon();
	StartupSalvage.preInitCommon();
	StartupModify.preInitCommon();
	StartupGearFactory.preInitCommon();
	StartupMap.preInitCommon();

	MinecraftForge.EVENT_BUS.register(new PlayerUnitPackage());
	MinecraftForge.EVENT_BUS.register(new EntityUnitPackage());
	MinecraftForge.EVENT_BUS.register(new DmgNumPacket());
	MinecraftForge.EVENT_BUS.register(new ParticlePackage());
	MinecraftForge.EVENT_BUS.register(new WorldPackage());

	Network.registerMessage(PlayerUnitPackage.Handler.class, PlayerUnitPackage.class, 0, Dist.CLIENT);
	Network.registerMessage(EntityUnitPackage.Handler.class, EntityUnitPackage.class, 1, Dist.CLIENT);

	Network.registerMessage(2, DmgNumPacket.class, DmgNumPacket::encode, DmgNumPacket::decode,
		DmgNumPacket::handle);

	Network.registerMessage(ParticlePackage.Handler.class, ParticlePackage.class, 3, Dist.CLIENT);
	Network.registerMessage(WorldPackage.Handler.class, WorldPackage.class, 4, Dist.CLIENT);
	Network.registerMessage(MessagePackage.Handler.class, MessagePackage.class, 5, Dist.CLIENT);

	CapabilityManager.INSTANCE.register(EntityData.UnitData.class, new EntityData.Storage(),
		EntityData.DefaultImpl.class);

	CapabilityManager.INSTANCE.register(WorldData.IWorldData.class, new WorldData.Storage(),
		WorldData.DefaultImpl.class);

	CapabilityManager.INSTANCE.register(PlayerDeathItems.IPlayerDrops.class, new PlayerDeathItems.Storage(),
		PlayerDeathItems.DefaultImpl.class);

	DeferredWorkQueue.runLater(() -> {
	    PacketHandler.register(); // NetworkRegistry.createInstance

	});
    }

    @Override
    public void postInit(InterModProcessEvent event) {

    }

    @Override
    public void loadComplete(FMLLoadCompleteEvent event) {

    }

    @Override
    public String translate(String str) {
	return "";
    }

    @Override
    public void regRenders(ModelRegistryEvent evt) {

    }

}
