package com.robertx22.blocks.bases;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.inventory.container.Container;

public abstract class TileGui extends ContainerScreen {

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
