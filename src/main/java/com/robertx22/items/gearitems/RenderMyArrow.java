package com.robertx22.items.gearitems;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class RenderMyArrow extends ArrowRenderer<MyEntityArrow> {
    public static final ResourceLocation RES_SPECTRAL_ARROW = new ResourceLocation("textures/entity/projectiles/spectral_arrow.png");

    public RenderMyArrow(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(MyEntityArrow entity) {
        return RES_SPECTRAL_ARROW;
    }

}