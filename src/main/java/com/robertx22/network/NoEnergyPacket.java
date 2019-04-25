package com.robertx22.network;

import java.util.function.Supplier;

import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.config.ModConfig;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class NoEnergyPacket {

    public enum MessageTypes {
	NoEnergy, NoMana
    }

    public NoEnergyPacket() {
    }

    public static NoEnergyPacket decode(PacketBuffer buf) {

	NoEnergyPacket newpkt = new NoEnergyPacket();

	return newpkt;

    }

    public static void encode(NoEnergyPacket packet, PacketBuffer tag) {

    }

    public static void handle(final NoEnergyPacket pkt, Supplier<NetworkEvent.Context> ctx) {

	ctx.get().enqueueWork(() -> {
	    try {

		if (ModConfig.Client.SHOW_LOW_ENERGY_MANA_WARNING.get()) {

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