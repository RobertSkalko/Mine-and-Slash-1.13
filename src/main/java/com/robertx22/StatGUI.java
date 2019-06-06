package com.robertx22;

import com.robertx22.mmorpg.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IInteractionObject;

import javax.annotation.Nullable;

public class StatGUI extends GuiScreen implements IInteractionObject {

    public static final String ID = Ref.MODID + ":stats_screen_gui";

    int sizeY = 220;
    int sizeX = 175;

    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/stats_screen.png");

    // Returns true if the given x,y coordinates are within the given rectangle
    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX,
                                   int mouseY) {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }

    float textScale = 0.8F;

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {

        super.render(mouseX, mouseY, partialTicks);

        this.drawDefaultBackground();

        Minecraft.getInstance().getTextureManager().bindTexture(texture);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        drawTexturedModalRect(mc.mainWindow.getScaledWidth() / 2 - this.sizeX / 2, mc.mainWindow
                .getScaledHeight() / 2 - this.sizeY / 2, 0, 0, sizeX, sizeY);

        GlStateManager.scalef(textScale, textScale, textScale);
        this.drawString(Minecraft.getInstance().fontRenderer, "Test Stat", 0, 0, TextFormatting.GRAY
                .getColor());

    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory,
                                     EntityPlayer playerIn) {
        return new Container() {
            @Override
            public boolean canInteractWith(EntityPlayer playerIn) {
                return true;
            }
        };
    }

    @Override
    public String getGuiID() {
        return ID;
    }

    @Override
    public ITextComponent getName() {
        return new TextComponentString("Stats Screen");
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Nullable
    @Override
    public ITextComponent getCustomName() {
        return new TextComponentString("Stats Screen");
    }
}
