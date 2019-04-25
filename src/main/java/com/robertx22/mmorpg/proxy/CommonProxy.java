package com.robertx22.mmorpg.proxy;

import java.util.function.Supplier;

import com.robertx22.advanced_blocks.gear_factory_station.StartupGearFactory;
import com.robertx22.advanced_blocks.item_modify_station.StartupModify;
import com.robertx22.advanced_blocks.map_device.StartupMap;
import com.robertx22.advanced_blocks.repair_station.StartupRepair;
import com.robertx22.advanced_blocks.salvage_station.StartupSalvage;
import com.robertx22.customitems.ores.ItemOre;
import com.robertx22.dimensions.blocks.TileMapPortal;
import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.mmorpg.config.non_mine_items.Serialization;
import com.robertx22.mmorpg.gui.GuiHandlerAll;
import com.robertx22.mmorpg.registers.GearItemRegisters;
import com.robertx22.mmorpg.registers.RegisterPackets;
import com.robertx22.network.DmgNumPacket;
import com.robertx22.network.EntityUnitPackage;
import com.robertx22.network.ParticlePackage;
import com.robertx22.network.PlayerUnitPackage;
import com.robertx22.network.WorldPackage;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.unique_items.UniqueItemRegister;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CommonProxy implements IProxy {

    @Override
    public void preInit(FMLCommonSetupEvent event) {
	ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY,
		() -> GuiHandlerAll::getClientGuiElement);

	Serialization.generateConfig();

	UniqueItemRegister.registerAll();

	TileEntityType.register(Ref.MODID + ":map_portal_tile", TileEntityType.Builder.create(TileMapPortal::new));

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

	CapabilityManager.INSTANCE.register(EntityData.UnitData.class, new EntityData.Storage(),
		EntityData.DefaultImpl::new);

	CapabilityManager.INSTANCE.register(WorldData.IWorldData.class, new WorldData.Storage(),
		WorldData.DefaultImpl::new);

	DeferredWorkQueue.runLater(() -> {
	    RegisterPackets.register(); // NetworkRegistry.createInstance

	});

	if (ModConfig.Server.GENERATE_ORES) {

	    int amount = 7;

	    for (int i = 0; i < ItemOre.Blocks.values().size(); i++) {
		genOre(ItemOre.Blocks.get(i), amount--);
	    }

	}

    }

    private void genOre(Block block, int amount) {

	BiomeManager.getBiomes(BiomeManager.BiomeType.WARM)
		.forEach((BiomeManager.BiomeEntry biomeEntry) -> biomeEntry.biome.addFeature(
			GenerationStage.Decoration.UNDERGROUND_ORES,
			Biome.createCompositeFeature(Feature.MINABLE,
				new MinableConfig(MinableConfig.IS_ROCK, block.getDefaultState(), 12),
				Biome.COUNT_RANGE, new CountRangeConfig(amount, 30, 40, 70)

			)));

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
    public EntityPlayer getPlayerEntityFromContext(Supplier<Context> ctx) {
	return null;
    }

}
