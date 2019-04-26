package com.robertx22.mmorpg;

import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.robertx22.dimensions.MapManager;
import com.robertx22.items.ores.ItemOre;
import com.robertx22.items.unique_items.UniqueItemRegister;
import com.robertx22.mmorpg.config.ClientContainer;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.mmorpg.config.non_mine_items.Serialization;
import com.robertx22.mmorpg.gui.GuiHandlerClient;
import com.robertx22.mmorpg.proxy.ClientProxy;
import com.robertx22.mmorpg.proxy.IProxy;
import com.robertx22.mmorpg.proxy.ServerProxy;
import com.robertx22.mmorpg.registers.CapabilityRegister;
import com.robertx22.mmorpg.registers.CommandRegister;
import com.robertx22.mmorpg.registers.CurioClientRegister;
import com.robertx22.mmorpg.registers.CurioSlotRegister;
import com.robertx22.mmorpg.registers.GearItemRegisters;
import com.robertx22.mmorpg.registers.OreGenRegister;
import com.robertx22.mmorpg.registers.PacketRegister;
import com.robertx22.mmorpg.registers.RenderRegister;
import com.robertx22.uncommon.testing.TestManager;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

@Mod(Ref.MODID)
@Mod.EventBusSubscriber
public class MMORPG {

    public static final Logger LOGGER = LogManager.getLogger();

    public static IProxy proxy = (IProxy) DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    // public static Main instance;

    private static final String PROTOCOL_VERSION = Integer.toString(1);

    public static final SimpleChannel Network = NetworkRegistry.ChannelBuilder
	    .named(new ResourceLocation(Ref.MODID, "main_channel")).clientAcceptedVersions(PROTOCOL_VERSION::equals)
	    .serverAcceptedVersions(PROTOCOL_VERSION::equals).networkProtocolVersion(() -> PROTOCOL_VERSION)
	    .simpleChannel();

    public MMORPG() {
	// Main.instance = this;

	System.out.println("Starting Mine and Slash");

	ModLoadingContext.get().registerConfig(Type.CLIENT, ClientContainer.INSTANCE.clientSpec);

	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postInit);

	DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {

	    ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY,
		    () -> GuiHandlerClient::getClientGuiElement);

	    FMLJavaModLoadingContext.get().getModEventBus().addListener(RenderRegister::regRenders);
	    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

	});

	loadConfig(ClientContainer.clientSpec, FMLPaths.CONFIGDIR.get().resolve("mmorpg-client.toml"));

	// ForgeMod
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {

	final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave()
		.writingMode(WritingMode.REPLACE).build();
	configData.load();
	spec.setConfig(configData);
    }

    public void preInit(FMLCommonSetupEvent event) {

	System.out.println("Starting Setup");

	ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY,
		() -> GuiHandlerClient::getClientGuiElement);

	PacketRegister.register();
	UniqueItemRegister.register();
	GearItemRegisters.register();
	ItemOre.Register();
	CapabilityRegister.register();
	OreGenRegister.register();
	Serialization.generateConfig();
	proxy.preInit(event);

    }

    public void postInit(final InterModProcessEvent event) {

	proxy.postInit(event);
	CurioSlotRegister.reg();
	Serialization.loadConfig();

    }

    public void loadComplete(final FMLLoadCompleteEvent event) {

    }

    public void clientSetup(final FMLClientSetupEvent event) {

	CurioClientRegister.icons();
    }

    @SubscribeEvent
    public void start(FMLServerStartingEvent event) {
	MapManager.onStartServerRegisterDimensions();
	CommandRegister.Register();
	TestManager.RunAllTests();

    }

    @SubscribeEvent
    public void stop(FMLServerStoppedEvent event) {
	MapManager.onStopServerUnRegisterDimensions();
    }

    @SubscribeEvent
    public void stopping(FMLServerStoppingEvent event) {

    }

    @SubscribeEvent
    public static void onWorldLoad(FMLServerStartedEvent event) {

	if (ModConfig.Server.DISABLE_VANILLA_HP_REGEN) {
	    ServerLifecycleHooks.getCurrentServer().getGameRules().setOrCreateGameRule("naturalRegeneration", "false",
		    ServerLifecycleHooks.getCurrentServer());
	}

    }

    public static <MSG> void sendToClient(MSG msg, EntityPlayerMP player) {

	Network.sendTo(msg, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
    }

}
