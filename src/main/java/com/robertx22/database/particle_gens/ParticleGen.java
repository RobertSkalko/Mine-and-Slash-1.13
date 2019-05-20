package com.robertx22.database.particle_gens;

import com.robertx22.WaterParticle;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

import java.util.Random;

public abstract class ParticleGen {

    public Random rand = new Random();

    public abstract void Summon(double x, double y, double z, double xVel, double yVel,
                                double zVel, double radius, int amount, Elements element);

    public abstract String Name();

    public Particle getParticleByElement(Minecraft mc, Elements element, World world,
                                         double x, double y, double z) {

        Particle water = new WaterParticle(mc.getTextureManager(), world, x, y, z, 1, 1, 1);

        return water;
    }

}
