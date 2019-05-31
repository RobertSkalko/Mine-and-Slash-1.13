package com.robertx22.items.gearitems.offhands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.concurrent.Callable;

public class MagicOrbRenderer extends TileEntityItemStackRenderer implements Callable<TileEntityItemStackRenderer> {
    public final TileEntityItemStackRenderer instance;

    private final ModelOrb orbModel = new ModelOrb();

    static int rotation = 0;

    public MagicOrbRenderer() {
        instance = this;
    }

    @Override
    public void renderByItem(ItemStack stack) {
        Item item = stack.getItem();

        if (item instanceof MagicOrb) {
            rotation++;
            MagicOrb orb = (MagicOrb) item;

            Minecraft.getInstance().getTextureManager().bindTexture(orb.resource);
            GlStateManager.pushMatrix();
            GlStateManager.scaled(1.0, -1.0, -1.0);
            //GlStateManager.rotatef(RandomUtils.RandomRange(0, 356), rotation, 0, rotation);

            new ModelOrb().render();
            GlStateManager.popMatrix();

        }

    }

    @Override
    public TileEntityItemStackRenderer call() throws Exception {
        return instance;
    }
}


