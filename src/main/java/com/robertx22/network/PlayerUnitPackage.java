package com.robertx22.network;

import java.util.function.Supplier;

import com.robertx22.mmorpg.Main;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlayerUnitPackage {

    public PlayerUnitPackage() {

    }

    private NBTTagCompound nbt;

    public PlayerUnitPackage(NBTTagCompound nbt) {
	this.nbt = nbt;
    }

    public static PlayerUnitPackage decode(PacketBuffer buf) {

	PlayerUnitPackage newpkt = new PlayerUnitPackage();

	newpkt.nbt = buf.readCompoundTag();

	return newpkt;

    }

    public static void encode(PlayerUnitPackage packet, PacketBuffer tag) {

	tag.writeCompoundTag(packet.nbt);

    }

    public static void handle(final PlayerUnitPackage pkt, Supplier<NetworkEvent.Context> ctx) {

	ctx.get().enqueueWork(() -> {
	    try {

		final EntityPlayer player = Main.proxy.getPlayerEntityFromContext(ctx);

		if (player != null) {

		    Load.Unit(player).setNBT(pkt.nbt);

		}

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	});

	ctx.get().setPacketHandled(true);

    }

}
