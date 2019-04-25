package com.robertx22.uncommon.utilityclasses;

import com.robertx22.mmorpg.Main;
import com.robertx22.network.ParticlePackage;

import net.minecraft.entity.Entity;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.network.PacketDistributor;

public class ParticleUtils {

    public static void spawnParticleGenerator(Entity source, String name, double x, double y, double z, double xVel,
	    double yVel, double zVel, double radius, int amount) {

	ParticlePackage packet = new ParticlePackage(name, x, y, z, xVel, yVel, zVel, radius, amount);

	Chunk chunk = source.world.getChunk(source.getPosition());
	Main.Network.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), packet);

    }

}
