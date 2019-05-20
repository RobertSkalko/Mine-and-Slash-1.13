package com.robertx22.database.particle_gens;

import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;

public class AoeProjectileParticleGen extends ParticleGen {

    @Override
    public void Summon(double x, double y, double z, double xVel, double yVel,
                       double zVel, double radius, int amount, Elements element) {
        for (int i = 0; i < amount; i++) {

            double u = Math.random();
            double v = Math.random();
            double theta = 2 * Math.PI * u;
            double phi = Math.acos(2 * v - 1);
            double xpos = x + (radius * Math.sin(phi) * Math.cos(theta));
            double ypos = y + (radius * Math.sin(phi) * Math.sin(theta));
            double zpos = z + (radius * Math.cos(phi));

            Particle particle = this.getParticleByElement(Minecraft.getInstance(), element, Minecraft
                    .getInstance().world, xpos, ypos, zpos);

            Minecraft.getInstance().particles.addEffect(particle);

        }
    }

    @Override
    public String Name() {
        return "AoeProjectileParticleGen";
    }

}
