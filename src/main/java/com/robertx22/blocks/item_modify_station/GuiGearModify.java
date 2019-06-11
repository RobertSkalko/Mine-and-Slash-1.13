package com.robertx22.blocks.item_modify_station;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.blocks.bases.TileGui;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.Words;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class GuiGearModify extends TileGui<ContainerGearModify> {

    // This is the resource location for the background image
    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/modify_station.png");

    TileGearModify tile = new TileGearModify();

    public GuiGearModify(ContainerGearModify cont, PlayerInventory invPlayer,
                         ITextComponent comp) {
        super(cont, invPlayer, comp);

        xSize = 176;
        ySize = 207;

        TileEntity en = Minecraft.getInstance().world.getTileEntity(cont.pos);

        if (en instanceof TileGearModify) {
            this.tile = (TileGearModify) en;
        }
    }

    // some [x,y] coordinates of graphical elements
    final int COOK_BAR_XPOS = 49;
    final int COOK_BAR_YPOS = 60;
    final int COOK_BAR_ICON_U = 0; // texture position of white arrow icon
    final int COOK_BAR_ICON_V = 207;
    final int COOK_BAR_WIDTH = 80;
    final int COOK_BAR_HEIGHT = 17;

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {

        TileEntity en = Minecraft.getInstance().world.getTileEntity(this.getContainer().pos);

        if (en instanceof TileGearModify) {
            this.tile = (TileGearModify) en;
        }

        // Bind the image texture
        Minecraft.getInstance().

                getTextureManager().

                bindTexture(texture);
        // Draw the image
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        blit(guiLeft, guiTop, 0, 0, xSize, ySize);

        // draw the cook progress bar
        blit(guiLeft + COOK_BAR_XPOS, guiTop + COOK_BAR_YPOS, COOK_BAR_ICON_U, COOK_BAR_ICON_V, (int) (this.tile
                .fractionOfCookTimeComplete() * COOK_BAR_WIDTH), COOK_BAR_HEIGHT);

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        final int LABEL_XPOS = 5;
        final int LABEL_YPOS = 5;
        this.font.drawString(Words.Gear_Modify.translate(), LABEL_XPOS, LABEL_YPOS, Color.darkGray
                .getRGB());

        List<String> hoveringText = new ArrayList<String>();

        // If the mouse is over the progress bar add the progress bar hovering text
        if (isInRect(guiLeft + COOK_BAR_XPOS, guiTop + COOK_BAR_YPOS, COOK_BAR_WIDTH, COOK_BAR_HEIGHT, mouseX, mouseY)) {
            hoveringText.add(Words.Progress.translate() + ": ");
            int cookPercentage = (int) (this.tile.fractionOfCookTimeComplete() * 100);
            hoveringText.add(cookPercentage + "%");
        }

        // If hoveringText is not empty draw the hovering text
        if (!hoveringText.isEmpty()) {

            renderTooltip(hoveringText, mouseX - guiLeft, mouseY - guiTop, font);
        }
    }

    // Returns true if the given x,y coordinates are within the given rectangle
    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX,
                                   int mouseY) {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }
}