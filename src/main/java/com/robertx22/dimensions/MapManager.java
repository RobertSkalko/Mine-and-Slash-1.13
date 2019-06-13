package com.robertx22.dimensions;

import com.robertx22.database.world_providers.IWP;
import com.robertx22.db_lists.WorldProviders;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.PlayerMapData;
import com.robertx22.uncommon.datasaving.Load;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class MapManager {

    @Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class EventMod {
        @SubscribeEvent
        public static void registerModDimensions(
                RegistryEvent.Register<ModDimension> event) {

            for (IWP iwp : WorldProviders.All.values()) {

                ModDimension moddim = iwp.newModDimension();

                if (moddim.getRegistryName() == null) {
                    moddim.setRegistryName(iwp.getResourceLoc());
                }

                event.getRegistry().register(moddim);

                iwp.setModDimension(moddim);

            }

        }

    }

    @Mod.EventBusSubscriber
    public static class EventDim {
        @SubscribeEvent
        public static void registerAllModDims(RegisterDimensionsEvent event) {
            for (IWP iwp : WorldProviders.All.values()) {

                ResourceLocation res = iwp.getResourceLoc();

                ModDimension moddim = iwp.getModDim();

                if (moddim.getRegistryName() == null) {
                    moddim.setRegistryName(iwp.getResourceLoc().toString());
                }

                if (isRegistered(iwp.getResourceLoc()) == false) {

                    DimensionManager.registerDimension(res, moddim, new PacketBuffer(Unpooled
                            .buffer()), true);
                }
            }

        }
    }

    public static DimensionType getDimension(ResourceLocation res) {

        return DimensionType.byName(res);

    }

    public static boolean isRegistered(ResourceLocation res) {

        return DimensionType.byName(res) != null;
    }

    public static DimensionType getDimensionType(ResourceLocation res) {
        return DimensionType.byName(res);
    }

    public static DimensionType register(ResourceLocation res, IWP IWPType) {

        DimensionType type = getDimensionType(res);

        if (type != null) {
            return type;
        } else {

            ModDimension moddim = IWPType.getModDim();

            return DimensionManager.registerDimension(res, moddim, null, true);
        }
    }

    public static DimensionType fromResource(ResourceLocation res) {
        return DimensionType.byName(res);
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

            return null;
        }
    }

    public static ResourceLocation getResourceLocation(DimensionType type) {

        ResourceLocation loc = DimensionType.getKey(type);

        return loc;
    }

    public static DimensionType setupPlayerMapDimension(PlayerEntity player,
                                                        UnitData unit, MapItemData map,
                                                        BlockPos pos) {

        DimensionType type = getDimension(map.getIWP().getResourceLoc());

        ResourceLocation res = getResourceLocation(type);

        unit.setCurrentMapId(res.toString());

        PlayerMapData.IPlayerMapData data = Load.playerMapData(player);

        data.init(pos, map, type, player);

        return type;

    }

    public static MinecraftServer getServer() {
        return ServerLifecycleHooks.getCurrentServer();
    }

}
