package com.robertx22.items.gearitems.offhands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelOrb extends ModelBase {
    private final ModelRenderer plate;

    public ModelOrb() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.plate = new ModelRenderer(this, 0, 0);
        this.plate.addBox(-6.0F, -11.0F, -2.0F, 8, 8, 8, 0.0F);

    }

    public void render() {

        int ticks = Minecraft.getInstance().player.ticksExisted;

        // this.plate.setRotationPoint(RandomUtils.RandomRange(0, 360), 0, 0);
        this.plate.render(0.0625F);
    }
}