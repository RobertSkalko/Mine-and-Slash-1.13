package com.robertx22.items.bags;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public abstract class BaseBagGui extends GuiContainer {

    public BaseBagGui(Container inventorySlotsIn) {
	super(inventorySlotsIn);
    }

    public abstract ResourceLocation texture();

    public abstract int rows();

    public abstract String name();

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
	this.drawDefaultBackground();
	super.render(mouseX, mouseY, partialTicks);
	this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
	this.fontRenderer.drawString(name(), 8, 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
	GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	this.mc.getTextureManager().bindTexture(texture());
	int i = (this.width - this.xSize) / 2;
	int j = (this.height - this.ySize) / 2;
	this.drawTexturedModalRect(i, j, 0, 0, this.xSize, rows() * 18 + 17);
	this.drawTexturedModalRect(i, j + rows() * 18 + 17, 0, 126, this.xSize, 96);
    }

}