package com.robertx22.network;

import java.util.function.Supplier;

import com.robertx22.database.particle_gens.ParticleGen;
import com.robertx22.db_lists.ParticleGens;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class ParticlePackage {

    private String name;
    private double x;
    private double y;
    private double z;
    private double xVel;
    private double yVel;
    private double zVel;
    private double radius;
    private int amount;

    public ParticlePackage() {
    }

    public ParticlePackage(String name, double x, double y, double z, double xVel, double yVel, double zVel,
	    double radius, int amount) {

	this.name = name;
	this.x = x;
	this.y = y;
	this.z = z;
	this.xVel = xVel;
	this.yVel = yVel;
	this.zVel = zVel;
	this.radius = radius;
	this.amount = amount;

    }

    public static ParticlePackage decode(PacketBuffer tag) {

	ParticlePackage newpkt = new ParticlePackage();

	newpkt.name = tag.readString(30);
	newpkt.x = tag.getDouble(0);
	newpkt.y = tag.getDouble(1);
	newpkt.z = tag.getDouble(2);
	newpkt.xVel = tag.getDouble(3);
	newpkt.yVel = tag.getDouble(4);
	newpkt.zVel = tag.getDouble(5);
	newpkt.radius = tag.getDouble(6);
	newpkt.amount = tag.getInt(7);

	return newpkt;

    }

    public static void encode(ParticlePackage packet, PacketBuffer tag) {

	tag.writeString(packet.name, 30);
	tag.setDouble(0, packet.x);
	tag.setDouble(1, packet.y);
	tag.setDouble(2, packet.z);
	tag.setDouble(3, packet.xVel);
	tag.setDouble(4, packet.yVel);
	tag.setDouble(5, packet.zVel);
	tag.setDouble(6, packet.radius);
	tag.setInt(7, packet.amount);

    }

    public static void handle(final ParticlePackage message, Supplier<NetworkEvent.Context> ctx) {

	ctx.get().enqueueWork(() -> {
	    try {

		ParticleGen gen = ParticleGens.All.get(message.name);

		gen.Summon(message.x, message.y, message.z, message.xVel, message.yVel, message.zVel, message.radius,
			message.amount);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	});

	ctx.get().setPacketHandled(true);

    }

}