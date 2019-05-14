package com.robertx22.mmorpg;

import com.mmorpg_libraries.curios.CurioClientSetup;
import com.mmorpg_libraries.curios.RegisterCurioSlots;
import com.mmorpg_libraries.neat_mob_overlay.HealthBarRenderer;
import com.robertx22.api.DatabaseIMCProcess;
import com.robertx22.config.ModConfig;
import com.robertx22.dimensions.MapManager;
import com.robertx22.mmorpg.proxy.ClientProxy;
import com.robertx22.mmorpg.proxy.IProxy;
import com.robertx22.mmorpg.proxy.ServerProxy;
import com.robertx22.mmorpg.registers.client.KeybindsRegister;
import com.robertx22.mmorpg.registers.common.CapabilityRegister;
import com.robertx22.mmorpg.registers.common.ConfigRegister;
import com.robertx22.mmorpg.registers.common.OreGenRegister;
import com.robertx22.mmorpg.registers.common.PacketRegister;
import com.robertx22.mmorpg.registers.server.CommandRegister;
import com.robertx22.uncommon.gui.GuiHandlerClient;
import com.robertx22.uncommon.gui.player_overlays.BarsGUI;
import com.robertx22.uncommon.testing.TestManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod(Ref.MODID)
@EventBusSubscriber
public class MMORPG {

    public static final Logger LOGGER = LogManager.getLogger();

    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    // public static Main instance;

    private static final String PROTOCOL_VERSION = Integer.toString(1);

    public static final SimpleChannel Network = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(Ref.MODID, "main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public MMORPG() {
        //ForgeMod
        // Main.instance = this; ForgeMod

        System.out.println("Starting Mine and Slash");

        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ConfigRegister.register(); // MUST BE IN MAIN CLASS
        ConfigRegister.load();  // MUST BE IN MAIN CLASS

        bus.addListener(this::commonSetupEvent);
        bus.addListener(this::interModProcessEvent);
        bus.addListener(this::interModEnqueue);
        bus.addListener(this::loadComplete);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {

            bus.addListener(this::clientSetup);

        });

    }

    public void commonSetupEvent(FMLCommonSetupEvent event) {

        System.out.println(Ref.MODID + ":FMLCommonSetupEvent");

        PacketRegister.register();
        CapabilityRegister.register();
        OreGenRegister.register();

    }

    private void interModEnqueue(final InterModEnqueueEvent event) {
        System.out.println(Ref.MODID + ":InterModEnqueueEvent");
        RegisterCurioSlots.register(event);

        // InterModComms.sendTo(Ref.MODID, "test", () -> new CompatibleItemMSG("itemid", new MyConfigItem()) {
        // });

    }

    private void interModProcessEvent(final InterModProcessEvent event) {
        System.out.println(Ref.MODID + ":InterModProcessEvent");
        DatabaseIMCProcess.proc(event);
    }

    public void loadComplete(final FMLLoadCompleteEvent event) {
        TestManager.RunAllTests();

    }

    public void clientSetup(final FMLClientSetupEvent event) {

        CurioClientSetup.setup(event);
        MinecraftForge.EVENT_BUS.register(new BarsGUI(Minecraft.getInstance()));
        MinecraftForge.EVENT_BUS.register(new HealthBarRenderer());
        KeybindsRegister.register();
        ModLoadingContext.get()
                .registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> GuiHandlerClient::getClientGuiElement);

    }

    @SubscribeEvent
    public static void onServerStarting(FMLServerStartingEvent event) {
        MapManager.onStartServerRegisterDimensions();
        CommandRegister.Register(event);

    }

    @SubscribeEvent
    public static void onServerStop(FMLServerStoppedEvent event) {
        MapManager.onStopServerUnRegisterDimensions();
    }

    @SubscribeEvent
    public static void onServerStopping(FMLServerStoppingEvent event) {

    }

    @SubscribeEvent
    public static void onServerStarted(FMLServerStartedEvent event) {

        // bandaid fix for config value nullpointer hopefully (DAMNIT NOW ENTITYDATA GIVES NULLPOINTER)
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable noteThread = new Runnable() {
            @Override
            public void run() {

                try {

                    if (ModConfig.INSTANCE.Server.DISABLE_VANILLA_HP_REGEN.get()) {
                        ServerLifecycleHooks.getCurrentServer()
                                .getGameRules()
                                .setOrCreateGameRule("naturalRegeneration", "false", ServerLifecycleHooks
                                        .getCurrentServer());
                    }

                    scheduler.shutdown();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        scheduler.schedule(noteThread, 5, TimeUnit.SECONDS);

    }

    public static <MSG> void sendToTracking(MSG msg, Entity entity) {

        if (msg == null) {
            System.out.println("msg is null wtf");
            return;
        }

        Network.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), msg);

        if (entity instanceof EntityPlayer) {
            sendToClient(msg, (EntityPlayerMP) entity);
        }

    }

    public static <MSG> void sendToClient(MSG msg, EntityPlayerMP player) {

        Network.sendTo(msg, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
    }

}
