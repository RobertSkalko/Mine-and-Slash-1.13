package com.robertx22.database.particle_gens;

import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.RedstoneParticleData;

import java.util.Random;

public abstract class ParticleGen {

    public Random rand = new Random();

    public abstract void Summon(double x, double y, double z, double radius, int amount,
                                Elements element);

    public abstract String Name();

    public void spawnRedstone(Elements element, double xpos, double ypos, double zpos) {

        Elements.RGB color = element.color;
        RedstoneParticleData data = new RedstoneParticleData(color.getR(), color.getG(), color
                .getB(), 1F);
        Minecraft.getInstance().world.addParticle(data, true, xpos, ypos, zpos, 1, 1, 1);
    }

}
