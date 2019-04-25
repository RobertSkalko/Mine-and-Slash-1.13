package com.robertx22.dimensions;

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

    public static void register(ResourceLocation res, ModDimension moddim) {

	DimensionManager.registerDimension(res, moddim, new PacketBuffer(Unpooled.wrappedBuffer(new byte[] {})));
    }

    public static void unRegister(DimensionType type) {

	DimensionManager.unregisterDimension(type.getId());

    }

    public static void onStartServerRegisterDimensions() {

    }

    public static void onStopServerUnRegisterDimensions() {

    }
}
