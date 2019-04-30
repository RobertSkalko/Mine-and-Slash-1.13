package com.robertx22.mmorpg;

import com.robertx22.config.ModConfig;
import com.robertx22.dimensions.MapManager;
import com.robertx22.mmorpg.proxy.ClientProxy;
import com.robertx22.mmorpg.proxy.IProxy;
import com.robertx22.mmorpg.proxy.ServerProxy;
import com.robertx22.mmorpg.registers.client.CurioClientRegister;
import com.robertx22.mmorpg.registers.client.RenderRegister;
import com.robertx22.mmorpg.registers.common.*;
import com.robertx22.mmorpg.registers.server.CommandRegister;
import com.robertx22.uncommon.gui.GuiHandlerClient;
import com.robertx22.uncommon.testing.TestManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
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

@Mod(Ref.MODID)
@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MMORPG {

    public static final Logger LOGGER = LogManager.getLogger();

    public static IProxy proxy = (IProxy) DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    // public static Main instance;

    private static final String PROTOCOL_VERSION = Integer.toString(1);

    public static final SimpleChannel Network = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(Ref.MODID, "main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public MMORPG() {
        // Main.instance = this;

        System.out.println("Starting Mine and Slash");

        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::preInit);
        bus.addListener(this::postInit);
        bus.addListener((this::onServerStarting));

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {

            ModLoadingContext.get()
                    .registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> GuiHandlerClient::getClientGuiElement);

            FMLJavaModLoadingContext.get()
                    .getModEventBus()
                    .addListener(RenderRegister::regRenders);
            FMLJavaModLoadingContext.get()
                    .getModEventBus()
                    .addListener(this::clientSetup);

        });

    }

    public void preInit(FMLCommonSetupEvent event) {

        System.out.println("Starting Setup");

        ModLoadingContext.get()
                .registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> GuiHandlerClient::getClientGuiElement);

        ConfigRegister.register();
        ConfigRegister.load();
        PacketRegister.register();
        CapabilityRegister.register();
        OreGenRegister.register();
        proxy.preInit(event);
    }

    public void postInit(final InterModProcessEvent event) {

        proxy.postInit(event);
        CurioSlotRegister.reg();

    }

    public void loadComplete(final FMLLoadCompleteEvent event) {

    }

    public void clientSetup(final FMLClientSetupEvent event) {

        CurioClientRegister.icons();
    }

    public void onServerStarting(FMLServerStartingEvent event) {
        MapManager.onStartServerRegisterDimensions();
        TestManager.RunAllTests();
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

        if (ModConfig.INSTANCE.Server.DISABLE_VANILLA_HP_REGEN.get()) {
            ServerLifecycleHooks.getCurrentServer()
                    .getGameRules()
                    .setOrCreateGameRule("naturalRegeneration", "false", ServerLifecycleHooks
                            .getCurrentServer());
        }

    }

    public static <MSG> void sendToTracking(MSG msg, EntityLivingBase entity) {

        if (msg == null) {
            System.out.println("msg is null wtf");
            return;
        }

        Chunk chunk = entity.world.getChunk(entity.getPosition());

        PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_CHUNK.with(() -> chunk);
        if (target != null) {
            Network.send(target, msg);

        }

    }

    public static <MSG> void sendToClient(MSG msg, EntityPlayerMP player) {

        Network.sendTo(msg, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
    }

}
