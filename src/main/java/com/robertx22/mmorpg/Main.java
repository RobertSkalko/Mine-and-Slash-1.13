package com.robertx22.mmorpg;

import com.robertx22.advanced_blocks.gear_factory_station.StartupGearFactory;
import com.robertx22.advanced_blocks.item_modify_station.StartupModify;
import com.robertx22.advanced_blocks.map_device.StartupMap;
import com.robertx22.advanced_blocks.repair_station.StartupRepair;
import com.robertx22.advanced_blocks.salvage_station.StartupSalvage;
import com.robertx22.customitems.ores.ItemOre;
import com.robertx22.dimensions.ChestGenerator;
import com.robertx22.dimensions.blocks.TileMapPortal;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.mmorpg.config.non_mine_items.Serialization;
import com.robertx22.mmorpg.proxy.ClientProxy;
import com.robertx22.mmorpg.proxy.IProxy;
import com.robertx22.mmorpg.proxy.ServerProxy;
import com.robertx22.mmorpg.registers.CommandRegisters;
import com.robertx22.mmorpg.registers.GearItemRegisters;
import com.robertx22.network.DamageNumberPackage;
import com.robertx22.network.EntityUnitPackage;
import com.robertx22.network.MessagePackage;
import com.robertx22.network.ParticlePackage;
import com.robertx22.network.PlayerUnitPackage;
import com.robertx22.network.WorldPackage;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.MapDatas;
import com.robertx22.uncommon.capability.PlayerDeathItems;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.oregen.OreGen;
import com.robertx22.uncommon.testing.TestManager;
import com.robertx22.unique_items.UniqueItemRegister;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@Mod.EventBusSubscriber
@Mod(Ref.MODID)
public class Main {

    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public static Main instance;

    private static final String PROTOCOL_VERSION = Integer.toString(1);

    public static final SimpleChannel Network = NetworkRegistry.ChannelBuilder
	    .named(new ResourceLocation(Ref.MODID, "main_channel")).clientAcceptedVersions(PROTOCOL_VERSION::equals)
	    .serverAcceptedVersions(PROTOCOL_VERSION::equals).networkProtocolVersion(() -> PROTOCOL_VERSION)
	    .simpleChannel();

    public Main() {
	Main.instance = this;

	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);

	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    }

    public void preInit(FMLCommonSetupEvent event) {

	Serialization.generateConfig(event);

	UniqueItemRegister.registerAll();

	GameRegistry.registerTileEntity(TileMapPortal.class, new ResourceLocation(Ref.MODID, "map_portal_tile"));

	proxy.preInit(event);

	proxy.registerRenders();

	GearItemRegisters.register();

	ItemOre.Register();
	StartupRepair.preInitCommon(event);
	StartupSalvage.preInitCommon(event);
	StartupModify.preInitCommon(event);
	StartupGearFactory.preInitCommon(event);
	StartupMap.preInitCommon(event);

	MinecraftForge.EVENT_BUS.register(new PlayerUnitPackage());
	MinecraftForge.EVENT_BUS.register(new EntityUnitPackage());
	MinecraftForge.EVENT_BUS.register(new DamageNumberPackage());
	MinecraftForge.EVENT_BUS.register(new ParticlePackage());
	MinecraftForge.EVENT_BUS.register(new WorldPackage());

	Network.registerMessage(PlayerUnitPackage.Handler.class, PlayerUnitPackage.class, 0, Dist.CLIENT);
	Network.registerMessage(EntityUnitPackage.Handler.class, EntityUnitPackage.class, 1, Dist.CLIENT);
	Network.registerMessage(DamageNumberPackage.Handler.class, DamageNumberPackage.class, 2, Dist.CLIENT);
	Network.registerMessage(ParticlePackage.Handler.class, ParticlePackage.class, 3, Dist.CLIENT);
	Network.registerMessage(WorldPackage.Handler.class, WorldPackage.class, 4, Dist.CLIENT);
	Network.registerMessage(MessagePackage.Handler.class, MessagePackage.class, 5, Dist.CLIENT);

	CapabilityManager.INSTANCE.register(EntityData.UnitData.class, new EntityData.Storage(),
		EntityData.DefaultImpl.class);

	CapabilityManager.INSTANCE.register(WorldData.IWorldData.class, new WorldData.Storage(),
		WorldData.DefaultImpl.class);

	CapabilityManager.INSTANCE.register(PlayerDeathItems.IPlayerDrops.class, new PlayerDeathItems.Storage(),
		PlayerDeathItems.DefaultImpl.class);

	ModMetadata modMeta = event.getModMetadata();

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

	RabbitGui.proxy.init();
	proxy.init(event);

	TestManager.RunAllTests();

	// RegisterBiomes.initBiomeManagerAndDictionary();

	if (ModConfig.Server.GENERATE_ORES) {
	    int chance = 6;
	    int amount = 7;

	    for (int i = 0; i < ItemOre.Blocks.values().size(); i++) {
		GameRegistry.registerWorldGenerator(new OreGen(ItemOre.Blocks.get(i), amount - i, 10, 75, chance - i),
			0);
	    }

	}

	GameRegistry.registerWorldGenerator(new ChestGenerator(), 5);

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

	proxy.postInit(event);
	RabbitGui.proxy.postInit();

	Serialization.generateConfig(event);

	Serialization.loadConfig(event);

    }

    @EventHandler
    public void start(FMLServerStartingEvent event) {

	registerDims(event);

	proxy.serverStarting(event);

	CommandRegisters.Register(event);

    }

    @EventHandler
    public void stop(FMLServerStoppedEvent event) {
	MapDatas.unregisterDimensions(); // every save game has it's own dimensions, otherwise when you switch saves you
					 // also get dimensions from your last save, which isn't nice

    }

    @EventHandler
    public void stopping(FMLServerStoppingEvent event) {

    }

    private void registerDims(FMLServerStartingEvent event) {

	String name = MapDatas.getLoc();
	MapDatas data = (MapDatas) event.getServer().worlds[0].getMapStorage().getOrLoadData(MapDatas.class, name);
	if (data == null) {
	    event.getServer().worlds[0].getMapStorage().setData(name, new MapDatas(name));
	    data = (MapDatas) event.getServer().worlds[0].getMapStorage().getOrLoadData(MapDatas.class, name);
	}

	data.registerDimensions();
    }

    @EventHandler
    public static void onWorldLoad(FMLServerStartedEvent event) {
	WorldServer world = DimensionManager.getWorld(0); // default world

	if (world != null) {
	    if (ModConfig.Server.DISABLE_VANILLA_HP_REGEN) {
		world.getGameRules().setOrCreateGameRule("naturalRegeneration", "false");
	    }
	}

    }
}
