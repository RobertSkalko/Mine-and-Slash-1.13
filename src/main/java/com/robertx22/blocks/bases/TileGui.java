package com.robertx22.blocks.bases;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public abstract class TileGui extends GuiContainer {

    public TileGui(Container inventorySlotsIn) {
        super(inventorySlotsIn);

    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {

        this.drawDefaultBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

    }
}
