package com.robertx22;

import com.robertx22.mmorpg.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WaterParticle extends MyBaseParticle {

    private static final ResourceLocation PARTICLE_TEXTURE = new ResourceLocation(Ref.MODID, "textures/particles/water.png");

    public WaterParticle(TextureManager textureManagerIn, World worldIn, double xCoordIn,
                         double yCoordIn, double zCoordIn, double xSpeed, double ySpeed,
                         double zSpeed) {
        super(textureManagerIn, worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeed, ySpeed, zSpeed);
    }

    @Override
    public ResourceLocation particleTexutre() {
        return PARTICLE_TEXTURE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        @Override
        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x,
                                     double y, double z, double xSpeed, double ySpeed,
                                     double zSpeed) {
            return new WaterParticle(Minecraft.getInstance()
                    .getTextureManager(), worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}
