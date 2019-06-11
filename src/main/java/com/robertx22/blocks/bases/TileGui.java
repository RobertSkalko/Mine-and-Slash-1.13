package com.robertx22.blocks.bases;

import com.robertx22.mmorpg.MMORPG;
import com.robertx22.network.RequestTilePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;

public abstract class TileGui<T extends BaseTileContainer, Tile extends BaseTile> extends ContainerScreen<T> {

    public Tile tile;

    Minecraft mc;

    public TileGui(T cont, PlayerInventory inv, ITextComponent text, Class<Tile> token) {
        super(cont, inv, text);

        this.mc = Minecraft.getInstance();

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

        if (mc.player.ticksExisted % 20 == 0) {
            MMORPG.sendToServer(new RequestTilePacket(tile.getPos(), mc.player));
        }

        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

    }
}
