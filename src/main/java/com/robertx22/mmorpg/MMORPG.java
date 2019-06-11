package com.robertx22.mmorpg;

import com.mmorpg_libraries.curios.CurioClientSetup;
import com.mmorpg_libraries.curios.GenerateCurioDataJsons;
import com.mmorpg_libraries.curios.RegisterCurioSlots;
import com.mmorpg_libraries.neat_mob_overlay.HealthBarRenderer;
import com.robertx22.config.ModConfig;
import com.robertx22.config.compatible_items.ConfigItemsSerialization;
import com.robertx22.db_lists.Initialization;
import com.robertx22.db_lists.Stats;
import com.robertx22.db_lists.bases.AllPreGenMapStats;
import com.robertx22.mmorpg.proxy.ClientProxy;
import com.robertx22.mmorpg.proxy.IProxy;
import com.robertx22.mmorpg.proxy.ServerProxy;
import com.robertx22.mmorpg.registers.client.KeybindsRegister;
import com.robertx22.mmorpg.registers.client.SpecialRenderRegister;
import com.robertx22.mmorpg.registers.common.*;
import com.robertx22.mmorpg.registers.server.CommandRegister;
import com.robertx22.onevent.world.OnStartResetMaps;
import com.robertx22.uncommon.develeper.CreateLangFile;
import com.robertx22.uncommon.gui.player_overlays.BarsGUI;
import com.robertx22.uncommon.testing.TestManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
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

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod(Ref.MODID)
@EventBusSubscriber
public class MMORPG {

    // DISABLE WHEN PUBLIC BUILD
    private static boolean RUN_DEV_TOOLS = true;

    public static final Logger LOGGER = LogManager.getLogger();

    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    private static final String PROTOCOL_VERSION = Integer.toString(1);

    public static final SimpleChannel Network = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(Ref.MODID, "main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public MMORPG() {

        System.out.println("Starting Mine and Slash");

        OnStartResetMaps.OnStartResetMaps();

        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ConfigRegister.register(); // MUST BE IN MAIN CLASS
        ConfigRegister.load();  // MUST BE IN MAIN CLASS

        Initialization.initAllDatabases(); // after config init

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
        WorldGenRegisters.register();
        ContainerGuiRegisters.reg();

    }

    private void interModEnqueue(final InterModEnqueueEvent event) {
        System.out.println(Ref.MODID + ":InterModEnqueueEvent");
        RegisterCurioSlots.register(event);

    }

    private void interModProcessEvent(final InterModProcessEvent event) {
        System.out.println(Ref.MODID + ":InterModProcessEvent");
        ConfigItemsSerialization.INSTANCE.generateConfigTutorials();

    }

    public void loadComplete(final FMLLoadCompleteEvent event) {

        if (RUN_DEV_TOOLS) { // CHANGE ON PUBLIC BUILDS TO FALSE
            TestManager.RunAllTests();
            CreateLangFile.create();
            GenerateCurioDataJsons.generate();

        }

    }

    public void clientSetup(final FMLClientSetupEvent event) {

        SpecialRenderRegister.register(event);
        CurioClientSetup.setup(event);
        MinecraftForge.EVENT_BUS.register(new BarsGUI(Minecraft.getInstance()));
        MinecraftForge.EVENT_BUS.register(new HealthBarRenderer());
        KeybindsRegister.register();

    }

    @SubscribeEvent
    public static void onServerStarting(FMLServerStartingEvent event) {
        CommandRegister.Register(event.getServer());

    }

    @SubscribeEvent
    public static void onServerStop(FMLServerStoppedEvent event) {

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

        Stats.allPreGenMapStatLists = new AllPreGenMapStats(); // in case stat lists are made before all stats are added up by other mods?

    }

    public static <MSG> void sendToTracking(MSG msg, Entity entity) {

        if (msg == null || entity == null) {
            return;
        }

        Network.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), msg);

        if (entity instanceof PlayerEntity) {
            sendToClient(msg, (ServerPlayerEntity) entity);
        }

    }

    public static <MSG> void sendToTracking(MSG msg, BlockPos pos, World world) {

        if (msg == null || world == null) {
            return;
        }

        PacketDistributor.TargetPoint point = new PacketDistributor.TargetPoint(pos.getX(), pos
                .getY(), pos.getZ(), 50, world.getDimension().getType());

        Network.send(PacketDistributor.NEAR.with(() -> point), msg);

    }

    public static <MSG> void sendToClient(MSG msg, ServerPlayerEntity player) {

        Network.sendTo(msg, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
    }

    public static <MSG> void sentToServer(MSG msg) {

        Network.sendToServer(msg);
    }

}
