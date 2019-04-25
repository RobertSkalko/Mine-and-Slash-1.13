package com.robertx22.network;

import java.util.function.Supplier;

import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.config.ModConfig;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessagePackage {

    public enum MessageTypes {
	NoEnergy, NoMana
    }

    public MessagePackage() {
    }

    public static MessagePackage decode(PacketBuffer buf) {

	MessagePackage newpkt = new MessagePackage();

	return newpkt;

    }

    public static void encode(MessagePackage packet, PacketBuffer tag) {

    }

    public static void handle(final MessagePackage pkt, Supplier<NetworkEvent.Context> ctx) {

	ctx.get().enqueueWork(() -> {
	    try {

		if (ModConfig.Client.SHOW_LOW_ENERGY_MANA_WARNING) {

		    EntityPlayer player = Main.proxy.getPlayerEntityFromContext(ctx);
		    player.playSound(SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT, 0.5F, 0);

		}

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	});

	ctx.get().setPacketHandled(true);

    }

}