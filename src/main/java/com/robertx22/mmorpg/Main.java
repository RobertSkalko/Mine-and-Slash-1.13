package com.robertx22.mmorpg;

import com.robertx22.dimensions.MapManager;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.mmorpg.config.non_mine_items.Serialization;
import com.robertx22.mmorpg.gui.GuiHandlerAll;
import com.robertx22.mmorpg.proxy.ClientProxy;
import com.robertx22.mmorpg.proxy.CommonProxy;
import com.robertx22.mmorpg.proxy.IProxy;
import com.robertx22.mmorpg.proxy.ServerProxy;
import com.robertx22.mmorpg.registers.CommandRegisters;
import com.robertx22.mmorpg.registers.RegisterCurioClient;
import com.robertx22.mmorpg.registers.RegisterCurioSlot;
import com.robertx22.uncommon.testing.TestManager;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

@Mod.EventBusSubscriber
@Mod(Ref.MODID)
public class Main {

    public static IProxy proxy = (IProxy) DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public static Main instance;

    private static final String PROTOCOL_VERSION = Integer.toString(1);

    public static final SimpleChannel Network = NetworkRegistry.ChannelBuilder
	    .named(new ResourceLocation(Ref.MODID, "main_channel")).clientAcceptedVersions(PROTOCOL_VERSION::equals)
	    .serverAcceptedVersions(PROTOCOL_VERSION::equals).networkProtocolVersion(() -> PROTOCOL_VERSION)
	    .simpleChannel();

    public Main() {
	Main.instance = this;

	IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

	eventBus.addListener(this::setup);
	eventBus.addListener(this::postInit);
	eventBus.addListener(this::enqueue);

	DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {

	    ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY,
		    () -> GuiHandlerAll::getClientGuiElement);

	    eventBus.addListener(ClientProxy::regRenders);
	    eventBus.addListener(this::clientSetup);

	});

	// ForgeMod
    }

    public void setup(final FMLCommonSetupEvent event) {

	MinecraftForge.EVENT_BUS.register(this);
	new CommonProxy().preInit(event);
	proxy.preInit(event);

    }

    public void loadComplete(final FMLLoadCompleteEvent event) {

    }

    public void postInit(final InterModProcessEvent event) {

	proxy.postInit(event);

	Serialization.loadConfig();

    }

    public void clientSetup(final FMLClientSetupEvent event) {

	RegisterCurioClient.icons();
    }

    private void enqueue(final InterModEnqueueEvent evt) {

	RegisterCurioSlot.reg();
    }

    @SubscribeEvent
    public void start(FMLServerStartingEvent event) {

	TestManager.RunAllTests();

	MapManager.onStartServerRegisterDimensions();

	CommandRegisters.Register();

    }

    @SubscribeEvent
    public void stop(FMLServerStoppedEvent event) {
	// every save game has it's own dimensions, otherwise when you switch saves you
	// also get dimensions from your last save, which isn't nice

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
