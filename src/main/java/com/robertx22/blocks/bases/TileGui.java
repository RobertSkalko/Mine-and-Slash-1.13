package com.robertx22.blocks.bases;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;

public abstract class TileGui<T extends BaseTileContainer, Tile extends BaseTile> extends ContainerScreen<T> {

    public Tile tile;

    public TileGui(T cont, PlayerInventory inv, ITextComponent text, Class<Tile> token) {
        super(cont, inv, text);

        TileEntity en = Minecraft.getInstance().world.getTileEntity(cont.pos);

        if (en != null) {
            if (token.isAssignableFrom(en.getClass())) {
                this.tile = (Tile) en;
            }
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {

        if (tile == null) {
            return;
        }

        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

    }
}
