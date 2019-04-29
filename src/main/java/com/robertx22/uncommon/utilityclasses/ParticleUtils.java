package com.robertx22.uncommon.utilityclasses;

import com.robertx22.mmorpg.MMORPG;
import com.robertx22.network.ParticleGenPacket;
import net.minecraft.entity.Entity;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.network.PacketDistributor;

public class ParticleUtils {

    public static void spawnParticleGenerator(Entity source, String name, double x,
                                              double y, double z, double xVel,
                                              double yVel, double zVel, double radius,
                                              int amount) {

        ParticleGenPacket packet = new ParticleGenPacket(name, x, y, z, xVel, yVel, zVel, radius, amount);

        Chunk chunk = source.world.getChunk(source.getPosition());
        MMORPG.Network.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), packet);

    }

}
