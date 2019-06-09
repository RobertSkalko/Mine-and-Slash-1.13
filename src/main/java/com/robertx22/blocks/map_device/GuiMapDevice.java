package com.robertx22.blocks.map_device;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.blocks.bases.TileGui;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.Words;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class GuiMapDevice extends TileGui<ContainerMapDevice> {

    // This is the resource location for the background image
    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/map_device.png");

    public GuiMapDevice(ContainerMapDevice cont, PlayerInventory invPlayer,
                        ITextComponent comp) {

        super(cont, invPlayer, comp);

        xSize = 176;
        ySize = 207;

    }

    public GuiMapDevice(ContainerMapDevice cont, PlayerInventory invPlayer,
                        TileMapDevice tile) {

        super(cont, invPlayer, new StringTextComponent("Factory"));

        // Set the width and height of the gui
        xSize = 176;
        ySize = 207;

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {

        // Bind the image texture
        Minecraft.getInstance().getTextureManager().bindTexture(texture);
        // Draw the image
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, 0, 0, xSize, ySize);

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        final int LABEL_XPOS = 5;
        final int LABEL_YPOS = 5;
        font.drawString(Words.Map_Device.translate(), LABEL_XPOS, LABEL_YPOS, Color.darkGray
                .getRGB());

        final int TIER_XPOS = 35;
        final int TIER_YPOS = 105;
        String tier = Words.Tier.translate() + "+";
        font.drawString(tier, TIER_XPOS - font.getStringWidth(tier) / 2, TIER_YPOS, Color.darkGray
                .getRGB());

        final int LEVEL_XPOS = 143;
        final int LEVEL_YPOS = 105;
        String level = Words.Level.translate() + "+";
        font.drawString(level, LEVEL_XPOS - font.getStringWidth(level) / 2, LEVEL_YPOS, Color.darkGray
                .getRGB());

        final int MAP_XPOS = 90;
        final int MAP_YPOS = 15;
        String map = Words.Put_Map.translate();
        font.drawString(map, MAP_XPOS - font.getStringWidth(map) / 2, MAP_YPOS, Color.darkGray
                .getRGB());

        final int START_XPOS = 88;
        final int START_YPOS = 85;
        String start = Words.Start.translate();
        font.drawString(start, START_XPOS - font.getStringWidth(start) / 2, START_YPOS, Color.darkGray
                .getRGB());

    }

}