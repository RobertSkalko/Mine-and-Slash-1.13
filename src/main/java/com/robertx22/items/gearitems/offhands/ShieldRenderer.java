package com.robertx22.items.gearitems.offhands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.model.ModelShield;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.concurrent.Callable;

public class ShieldRenderer extends TileEntityItemStackRenderer implements Callable<TileEntityItemStackRenderer> {
    public final TileEntityItemStackRenderer instance;

    private final ModelShield modelShield = new ModelShield();

    public ShieldRenderer() {
        instance = this;
    }

    @Override
    public void renderByItem(ItemStack stack) {
        Item item = stack.getItem();

        if (item instanceof NormalShield) {

            NormalShield shield = (NormalShield) item;

            Minecraft.getInstance().getTextureManager().bindTexture(shield.resource);
            GlStateManager.pushMatrix();
            GlStateManager.scaled(1F, -0.6F, -1.0);
            modelShield.render();
            GlStateManager.popMatrix();

        }

    }

    @Override
    public TileEntityItemStackRenderer call() throws Exception {
        return instance;
    }
}

