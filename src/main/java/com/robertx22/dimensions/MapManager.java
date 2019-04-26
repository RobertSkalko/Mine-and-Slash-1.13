package com.robertx22.dimensions;

import com.robertx22.db_lists.WorldProviders;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;

public class MapManager {

    public static boolean isRegistered(ResourceLocation res) {

	return DimensionType.byName(res) != null;
    }

    public static void register(String name, String IWPType) {

	ModDimension dim = WorldProviders.All.get(IWPType).newModDimension(name);

	DimensionType type = DimensionManager.registerDimension(dim.getRegistryName(), dim,
		new PacketBuffer(Unpooled.wrappedBuffer(new byte[] {})));
    }

    public static void unRegister(ResourceLocation res) {

	DimensionManager.unregisterDimension(DimensionType.byName(res).getId());

    }

    public static void onStartServerRegisterDimensions() {

	// DimensionManager.getWorld(server, DimensionType.byName(res),
	// resetUnloadDelay, forceLoad)
    }

    /**
     * // every save game has it's own dimensions, otherwise when you switch saves
     * you // also get dimensions from your last save, which isn't nice
     */
    public static void onStopServerUnRegisterDimensions() {

    }
}
