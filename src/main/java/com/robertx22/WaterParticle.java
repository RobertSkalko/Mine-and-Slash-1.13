package com.robertx22;

import com.robertx22.mmorpg.Ref;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
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

}
