package com.robertx22.dimensions;

import com.robertx22.db_lists.WorldProviders;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.DimensionData;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.uncommon.capability.DimsData;
import com.robertx22.uncommon.capability.DimsData.IDimsData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.testing.Watch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
import java.util.Random;
import java.util.UUID;

@Mod.EventBusSubscriber
public class MapManager {

    @SubscribeEvent
    public static void registerAllModDims(RegisterDimensionsEvent event) {

        //IDimsData dims = getDimsData();

        MapsGson.INSTANCE.load();

        Watch total = new Watch();
        for (int i = 0; i < 100; i++) {
            MapManager.preRegisterDimension(MapsGson.INSTANCE);

        }

        MapsGson.INSTANCE.save(MapsGson.INSTANCE);

        total.print("Pre-Registering Dimensions took: ");
    }

    public static DimensionType getDimension(ResourceLocation res) {

        return DimensionType.byName(res);

    }

    public static boolean isRegistered(ResourceLocation res) {

        return DimensionType.byName(res) != null;
    }

    static Random rand = new Random();

    public static DimensionType getDimensionType(ResourceLocation res) {
        return DimensionType.byName(res);
    }

    static int num = 10;

    public static DimensionType register(ResourceLocation res, IWP IWPType) {

        DimensionType type = getDimensionType(res);

        if (type != null) {
            return type;
        } else {

            ModDimension moddim = IWPType.getModDim();

            return DimensionManager.registerDimension(res, moddim, null);
            //return DimensionManager.registerDimensionInternal(num++, res, moddim, null); i tried this too
        }
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
            World world = getWorld(type);
            if (world != null) {
                Load.World(world).passAllTime(world);
            }
        }

    }

    public static World getWorld(DimensionType type) {
        return getWorld(getResourceLocation(type).toString());
    }

    public static World getWorld(String res) {
        DimensionType type = getDimensionType(new ResourceLocation(res));

        if (type == null) {
            return null;
        }

        if (type == DimensionType.OVERWORLD) {
            return getServer().getWorld(type);
        } else {

            World world = DimensionManager.getWorld(getServer(), type, false, true);

            if (world != null) {
                return world;
            }

            return DimensionManager.initWorld(getServer(), type);
        }
    }

    public static ResourceLocation getResourceLocation(DimensionType type) {

        ResourceLocation loc = DimensionType.getKey(type);
        String str = loc.toString();

        if (str.contains(Ref.MODID) == false && str.contains("minecraft") == false) {
            loc = new ResourceLocation(Ref.MODID, loc.toString());
        }

        return loc;
    }

    public static DimensionType preRegisterDimension(MapsGson maps) {

        ResourceLocation res = randomResourceLoc();

        IWP iwp = WorldProviders.INSTANCE.random();

        DimensionType type = MapManager.register(res, iwp);

        //        DimensionManager.initWorld(getServer(), type);

        MapsGson.MapGson map = new MapsGson.MapGson();
        map.reg = res.toString();
        map.iwp = iwp.GUID();
        maps.list.add(map);

        // data.add(type, iwp);

        return type;

    }

    private static ResourceLocation randomResourceLoc() {
        ResourceLocation res = new ResourceLocation(Ref.MODID, UUID.randomUUID()
                .toString() + "_mine_and_slash_dim");

        while (MapManager.isRegistered(res)) {

            res = new ResourceLocation(Ref.MODID, UUID.randomUUID()
                    .toString() + "_mine_and_slash_dim");
        }

        return res;

    }

    public static DimensionType initNewDimension(World currentworld, EntityPlayer player,
                                                 UnitData unit, MapItemData map,
                                                 BlockPos pos) {

        freeCurrentDim(player, unit);

        IDimsData dims = getDimsData();

        DimensionData data = dims.getFreeDimension();
        DimensionType type = data.getDimensionType();
        ResourceLocation res = getResourceLocation(type);

        DimensionManager.initWorld(getServer(), type);

        World world = getWorld(type);

        unit.setCurrentMapId(res.toString());

        WorldData.IWorldData iworld = Load.World(world);

        iworld.init(pos, currentworld, map, res.toString(), player);

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

    public static MinecraftServer getServer() {
        return ServerLifecycleHooks.getCurrentServer();
    }

    public static IDimsData getDimsData() {

        // this shit is obsfucated so i opted for capability instead
        /*
        ServerLifecycleHooks.getCurrentServer()
                .getWorld(DimensionType.OVERWORLD)
                .getMapStorage().
                */

        IDimsData data = new DimsData.DefaultImpl();

        for (MapsGson.MapGson map : MapsGson.INSTANCE.list) {
            data.add(map.reg, map.iwp);
        }

        return data;

        //return Load.Dims(getServer().getWorld(DimensionType.OVERWORLD));
    }

}
