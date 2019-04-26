package com.robertx22.network;

import java.util.function.Supplier;

import com.robertx22.mmorpg.MMORPG;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

public class WorldPackage {

    private NBTTagCompound nbt;

    public WorldPackage() {
    }

    public WorldPackage(IWorldData data) {
	if (data != null) {
	    this.nbt = data.getNBT();
	}
    }

    public static WorldPackage decode(PacketBuffer buf) {

	WorldPackage newpkt = new WorldPackage();

	newpkt.nbt = buf.readCompoundTag();

	return newpkt;

    }

    public static void encode(WorldPackage packet, PacketBuffer tag) {

	tag.writeCompoundTag(packet.nbt);

    }

    public static void handle(final WorldPackage pkt, Supplier<NetworkEvent.Context> ctx) {

	ctx.get().enqueueWork(() -> {
	    try {

		final EntityPlayer player = MMORPG.proxy.getPlayerEntityFromContext(ctx);
		if (player != null) {
		    World world = player.world;
		    if (world != null) {
			Load.World(world).setNBT(pkt.nbt);
		    }
		}

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	});

	ctx.get().setPacketHandled(true);

    }

}