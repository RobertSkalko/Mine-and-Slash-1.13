package com.robertx22.network;

import com.robertx22.database.particle_gens.ParticleGen;
import com.robertx22.db_lists.ParticleGens;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ParticleGenPacket {

    private String name;
    private double x;
    private double y;
    private double z;
    private double xVel;
    private double yVel;
    private double zVel;
    private double radius;
    private int amount;
    private String element;

    public ParticleGenPacket() {
    }

    public ParticleGenPacket(String name, double x, double y, double z, double xVel,
                             double yVel, double zVel, double radius, int amount,
                             Elements element) {

        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.xVel = xVel;
        this.yVel = yVel;
        this.zVel = zVel;
        this.radius = radius;
        this.amount = amount;
        this.element = element.toString();

    }

    public static ParticleGenPacket decode(PacketBuffer tag) {

        ParticleGenPacket newpkt = new ParticleGenPacket();

        newpkt.name = tag.readString(30);
        newpkt.x = tag.readDouble();
        newpkt.y = tag.readDouble();
        newpkt.z = tag.readDouble();
        newpkt.xVel = tag.readDouble();
        newpkt.yVel = tag.readDouble();
        newpkt.zVel = tag.readDouble();
        newpkt.radius = tag.readDouble();
        newpkt.amount = tag.readInt();
        newpkt.element = tag.readString(30);

        return newpkt;

    }

    public static void encode(ParticleGenPacket packet, PacketBuffer tag) {

        tag.writeString(packet.name, 30);
        tag.writeDouble(packet.x);
        tag.writeDouble(packet.y);
        tag.writeDouble(packet.z);
        tag.writeDouble(packet.xVel);
        tag.writeDouble(packet.yVel);
        tag.writeDouble(packet.zVel);
        tag.writeDouble(packet.radius);
        tag.writeInt(packet.amount);
        tag.writeString(packet.element, 20);
    }

    public static void handle(final ParticleGenPacket message,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {

                ParticleGen gen = ParticleGens.All.get(message.name);

                gen.Summon(message.x, message.y, message.z, message.radius, message.amount, Elements
                        .valueOf(message.element));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}