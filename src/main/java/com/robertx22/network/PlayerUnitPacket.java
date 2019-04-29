package com.robertx22.network;

import com.robertx22.mmorpg.MMORPG;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerUnitPacket {

    public PlayerUnitPacket() {

    }

    private NBTTagCompound nbt;

    public PlayerUnitPacket(NBTTagCompound nbt) {
        this.nbt = nbt;
    }

    public static PlayerUnitPacket decode(PacketBuffer buf) {

        PlayerUnitPacket newpkt = new PlayerUnitPacket();
        newpkt.nbt = buf.readCompoundTag();
        return newpkt;

    }

    public static void encode(PlayerUnitPacket packet, PacketBuffer tag) {

        tag.writeCompoundTag(packet.nbt);

    }

    public static void handle(final PlayerUnitPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                final EntityPlayer player = MMORPG.proxy.getPlayerEntityFromContext(ctx);

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
