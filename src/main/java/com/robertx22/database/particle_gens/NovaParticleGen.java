package com.robertx22.database.particle_gens;

import net.minecraft.client.Minecraft;
import net.minecraft.particles.RedstoneParticleData;

public class NovaParticleGen extends ParticleGen {

    @Override
    public void Summon(double x, double y, double z, double xVel, double yVel, double zVel, double radius, int amount) {
	for (int i = 0; i < amount; i++) {

	    double u = Math.random();
	    double v = Math.random();
	    double theta = 2 * Math.PI * u;
	    double phi = Math.acos(2 * v - 1);
	    double xpos = x + (radius * Math.sin(phi) * Math.cos(theta));
	    double ypos = y;
	    double zpos = z + (radius * Math.cos(phi));

	    Minecraft.getInstance().world.addParticle(
		    new RedstoneParticleData((float) xVel, (float) yVel, (float) zVel, 1F), true, xpos, ypos, zpos, 1,
		    1, 1);

	}
    }

    @Override
    public String Name() {
	return "NovaParticleGen";
    }

}
