package com.robertx22.dimensions;

import java.util.UUID;

import com.robertx22.db_lists.WorldProviders;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.uncommon.capability.DimsData.IDimsData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;

import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class MapManager {

    public static boolean isRegistered(ResourceLocation res) {

	return DimensionType.byName(res) != null;
    }

    public static DimensionType register(ResourceLocation res, IWP IWPType) {

	ModDimension dim = WorldProviders.All.get(IWPType.GUID()).newModDimension(res);

	DimensionType type = DimensionManager.registerDimension(dim.getRegistryName(), dim,
		new PacketBuffer(Unpooled.wrappedBuffer(new byte[] {})));

	return type;
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

    public void createNewDim(EntityPlayer player, UnitData unit, MapItemData map) {

	ResourceLocation res = new ResourceLocation(unit.getCurrentMapId());

	while (MapManager.isRegistered(res)) {

	    res = new ResourceLocation(Ref.MODID, UUID.randomUUID().toString());
	}

	MapManager.register(res, map.getWorldProvider());

	unit.setCurrentMapId(res.toString());

    }

    public void freeCurrentDim(EntityPlayer player, UnitData unit) {

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

    public static IDimsData getDimsData() {
	return Load.Dims(ServerLifecycleHooks.getCurrentServer().getWorld(DimensionType.OVERWORLD));
    }

}
