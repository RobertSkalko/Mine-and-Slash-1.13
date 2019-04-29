package com.robertx22.network;

import com.robertx22.mmorpg.MMORPG;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class IWorldPacket {

    private NBTTagCompound nbt;

    public IWorldPacket() {
    }

    public IWorldPacket(IWorldData data) {
        if (data != null) {
            this.nbt = data.getNBT();
        }
    }

    public static IWorldPacket decode(PacketBuffer buf) {

        IWorldPacket newpkt = new IWorldPacket();

        newpkt.nbt = buf.readCompoundTag();

        return newpkt;

    }

    public static void encode(IWorldPacket packet, PacketBuffer tag) {

        tag.writeCompoundTag(packet.nbt);

    }

    public static void handle(final IWorldPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

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