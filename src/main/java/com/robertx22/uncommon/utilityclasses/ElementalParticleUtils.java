package com.robertx22.uncommon.utilityclasses;

import com.robertx22.database.particle_gens.AoeProjectileParticleGen;
import com.robertx22.database.particle_gens.NovaParticleGen;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class ElementalParticleUtils {

    public static void SpawnNovaParticle(Elements element, Entity entity, double radius,
                                         int amount) {

        String name = new NovaParticleGen().Name();

        ParticleUtils.spawnParticleGenerator(entity, name, entity.posX, entity.posY + entity.height / 2, entity.posZ, 1, 1, 1, radius, amount, element);
    }

    public static void SpawnAoeParticle(Elements element, Entity entity, double radius,
                                        int amount) {

        String name = new AoeProjectileParticleGen().Name();

        ParticleUtils.spawnParticleGenerator(entity, name, entity.posX, entity.posY, entity.posZ, 1, 1, 1, radius, amount, element);
    }

    public static void SpawnAoeParticle(Elements element, World world, double x, double y,
                                        double z, double radius, int amount) {

        String name = new AoeProjectileParticleGen().Name();

        ParticleUtils.spawnParticleGenerator(world, name, x, y, z, 1, 1, 1, radius, amount, element);
    }

}
