package com.robertx22.dimensions;

import com.robertx22.db_lists.WorldProviders;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.uncommon.capability.DimsData;
import com.robertx22.uncommon.capability.DimsData.IDimsData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Mod.EventBusSubscriber
public class MapManager {

    @SubscribeEvent
    public static void registerAllModDims(RegisterDimensionsEvent event) {

        for (IWP iwp : WorldProviders.All.values()) {
            iwp.setupModDim();
        }

    }

    public static DimensionType getDimension(ResourceLocation res) {

        return DimensionType.byName(res);

    }

    public static boolean isRegistered(ResourceLocation res) {

        return DimensionType.byName(res) != null;
    }

    public static DimensionType register(ResourceLocation res, IWP IWPType) {

        ModDimension dim = WorldProviders.All.get(IWPType.GUID()).moddim;

        return DimensionManager.registerDimension(dim.getRegistryName(), dim, new PacketBuffer(Unpooled
                .wrappedBuffer(new byte[]{})));

    }

    public static void unRegister(World world) {

        DimensionManager.unregisterDimension(world.getDimension().getType().getId());

        DimsData.IDimsData dims = MapManager.getDimsData();

        dims.remove(world);

        try {
            FileUtils.deleteDirectory(Objects.requireNonNull(WorldFileUtils.getWorldDirectory(world)));
            System.out.println("Deleting a temporary map world to free up disk space!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static DimensionType fromResource(ResourceLocation res) {
        return DimensionType.byName(res);
    }

    public static void expire(ResourceLocation res) {

        DimensionType type = DimensionType.byName(res);

        if (type != null) {
            World world = DimensionManager.getWorld(ServerLifecycleHooks.getCurrentServer(), type, false, true);
            if (world != null) {
                Load.World(world).passAllTime(world);
            }
        }

    }

    public static World getWorld(String res) {
        DimensionType type = DimensionType.byName(new ResourceLocation(res));
        return DimensionManager.initWorld(getServer(), type);

    }

    public static DimensionType createNewDim(World currentworld, EntityPlayer player,
                                             UnitData unit, MapItemData map,
                                             BlockPos pos) {

        freeCurrentDim(player, unit);

        ResourceLocation res = new ResourceLocation(Ref.MODID, UUID.randomUUID()
                .toString());

        while (MapManager.isRegistered(res)) {

            res = new ResourceLocation(Ref.MODID, UUID.randomUUID().toString());
        }

        DimensionType type = MapManager.register(res, map.getWorldProvider())
                .setRegistryName(res);

        WorldServer world = DimensionManager.initWorld(getServer(), type);

        unit.setCurrentMapId(res.toString());

        IWorldData iworld = Load.World(world);

        iworld.init(pos, currentworld, map, res.toString(), player);

        IDimsData dims = getDimsData();

        dims.add(type, map.getWorldProvider());

        return type;

    }

    private static void freeCurrentDim(EntityPlayer player, UnitData unit) {

        if (unit.hasCurrentMapId()) {

            ResourceLocation res = new ResourceLocation(unit.getCurrentMapId());

            MapManager.expire(res);

        }

    }

    public static void onStartServerRegisterDimensions() {
        getDimsData().registerAll();
    }

    /**
     * // every save game has it's own dimensions, otherwise when you switch saves
     * you // also get dimensions from your last save, which isn't nice
     */
    public static void onStopServerUnRegisterDimensions() {
        getDimsData().unregisterAll();
    }

    private static MinecraftServer getServer() {
        return ServerLifecycleHooks.getCurrentServer();
    }

    public static IDimsData getDimsData() {

        // this shit is obsfucated so i opted for capability instead
        /*
        ServerLifecycleHooks.getCurrentServer()
                .getWorld(DimensionType.OVERWORLD)
                .getMapStorage().
                */

        return Load.Dims(ServerLifecycleHooks.getCurrentServer()
                .getWorld(DimensionType.OVERWORLD));
    }

}
