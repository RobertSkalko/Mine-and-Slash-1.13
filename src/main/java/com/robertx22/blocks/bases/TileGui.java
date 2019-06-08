package com.robertx22.blocks.bases;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;

public abstract class TileGui extends ContainerScreen {

    public TileGui(Container cont, PlayerInventory inv, ITextComponent text) {
        super(cont, inv, text);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {

        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

    }
}
