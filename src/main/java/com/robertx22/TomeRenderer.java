package com.robertx22;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.concurrent.Callable;

//EnchantmentTableTileEntityRenderer
@OnlyIn(Dist.CLIENT)
public class TomeRenderer extends ItemStackTileEntityRenderer implements Callable<ItemStackTileEntityRenderer> {
    public final ItemStackTileEntityRenderer instance;

    private final TomeModel tomeModel = new TomeModel();
    Minecraft mc;

    public TomeRenderer() {
        instance = this;
        this.mc = Minecraft.getInstance();
    }

    float openPercent = 0.7F; //(0-1)

    @Override
    public void renderByItem(ItemStack stack) {
        Item item = stack.getItem();

        if (item instanceof TomeItem) {

            TomeItem tome = (TomeItem) item;

            float scale = 1.5F;
            float rotation = 180F;

            Minecraft.getInstance().getTextureManager().bindTexture(tome.texture);
            GlStateManager.pushMatrix();
            GlStateManager.rotatef(rotation, 0, 0, 0);
            GlStateManager.scaled(scale, -scale, -scale);

            GlStateManager.enableCull();

            float ticks = mc.getRenderPartialTicks() + mc.player.ticksExisted;

            tomeModel.render(ticks, 0, 0, openPercent, 0.0F, 0.0625F);
            GlStateManager.popMatrix();

        }

    }

    @Override
    public ItemStackTileEntityRenderer call() throws Exception {
        return instance;
    }
}

