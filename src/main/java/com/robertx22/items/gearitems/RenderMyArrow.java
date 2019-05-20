package com.robertx22.items.gearitems;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMyArrow extends RenderArrow<MyEntityArrow> {
    public static final ResourceLocation RES_SPECTRAL_ARROW = new ResourceLocation("textures/entity/projectiles/spectral_arrow.png");

    public RenderMyArrow(RenderManager manager) {
        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(MyEntityArrow entity) {
        return RES_SPECTRAL_ARROW;
    }

}