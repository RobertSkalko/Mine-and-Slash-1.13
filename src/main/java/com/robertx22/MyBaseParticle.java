package com.robertx22;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public abstract class MyBaseParticle extends Particle {
    private static final Random RANDOM = new Random();
    private final TextureManager textureManager;

    public abstract ResourceLocation particleTexutre();

    //  private static final VertexFormat VERTEX_FORMAT = (new VertexFormat()).addElement(DefaultVertexFormats.POSITION_3F).addElement(DefaultVertexFormats.TEX_2F).addElement(DefaultVertexFormats.COLOR_4UB).addElement(DefaultVertexFormats.TEX_2S).addElement(DefaultVertexFormats.NORMAL_3B).addElement(DefaultVertexFormats.PADDING_1B);
    //

    private static final VertexFormat VERTEX_FORMAT = new VertexFormat();

    protected MyBaseParticle(TextureManager textureManagerIn, World worldIn,
                             double xCoordIn, double yCoordIn, double zCoordIn,
                             double xSpeed, double ySpeed, double zSpeed) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
        this.textureManager = textureManagerIn;
        this.setColor(1.0F, 1.0F, 1.0F);

    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks,
                               float rotationX, float rotationZ, float rotationYZ,
                               float rotationXY, float rotationXZ) {

        this.textureManager.bindTexture(this.particleTexutre());
        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);

    }

    @Override
    public int getFXLayer() {
        return 3;
    }

}