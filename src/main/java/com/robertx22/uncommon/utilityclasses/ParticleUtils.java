package com.robertx22.uncommon.utilityclasses;

import com.robertx22.mmorpg.MMORPG;
import com.robertx22.network.ParticleGenPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Particles;
import net.minecraft.particles.IParticleData;

public class ParticleUtils {

    public static void spawnHealParticles(EntityLivingBase en, int amount) {

        spawnParticles(Particles.HEART, en, amount);

    }

    public static void spawnEnergyRestoreParticles(EntityLivingBase en, int amount) {

        spawnParticles(Particles.HAPPY_VILLAGER, en, amount);

    }

    public static void spawnManaRestoreParticles(EntityLivingBase en, int amount) {

        spawnParticles(Particles.BUBBLE, en, amount);

    }

    public static void spawnParticles(IParticleData particle, EntityLivingBase en,
                                      int amount) {
        for (int i = 0; i < amount; i++) {
            double d0 = (double) ((float) en.posX + en.world.rand.nextFloat() * 2 - 1.0F);
            // Apparently the client side spawns the particles 1 block higher than it
            // should... hence the -
            // 0.5F.
            double d1 = (double) ((float) WizardryUtilities.getPlayerEyesPos(en) - 0.5F + en.world.rand
                    .nextFloat());
            double d2 = (double) ((float) en.posZ + en.world.rand.nextFloat() * 2 - 1.0F);

            en.world.addParticle(particle, true, d0, d1, d2, 0, 48 + en.world.rand.nextInt(12), 1.0f);
        }
    }

    public static void spawnParticleGenerator(Entity source, String name, double x,
                                              double y, double z, double xVel,
                                              double yVel, double zVel, double radius,
                                              int amount) {

        ParticleGenPacket packet = new ParticleGenPacket(name, x, y, z, xVel, yVel, zVel, radius, amount);

        MMORPG.sendToTracking(packet, source);

    }

}
