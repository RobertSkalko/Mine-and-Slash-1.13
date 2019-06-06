package com.robertx22;

import com.robertx22.database.stats.Stat;
import com.robertx22.db_lists.Stats;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IInteractionObject;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StatGUI extends GuiScreen implements IInteractionObject {

    public static final String ID = Ref.MODID + ":stats_screen_gui";

    int sizeY = 220;
    int sizeX = 215;

    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/stats_screen.png");

    // Returns true if the given x,y coordinates are within the given rectangle
    public boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
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

        //GlStateManager.scalef(textScale, textScale, textScale);

        renderStats();

    }

    private int getTextStartX() {

        return (int) (mc.mainWindow.getScaledWidth() / 2 - this.sizeX / 2 + 15);
    }

    private int getTextStartY() {

        return (int) (mc.mainWindow.getScaledHeight() / 2 - this.sizeY / 2 + 40);
    }

    Stat.StatGroup statgroup = Stat.StatGroup.Misc;

    int currentElement = 0;

    private int renderStats() {

        EntityData.UnitData data = Load.Unit(mc.player);

        List<String> list = new ArrayList<>();

        for (Stat stat : Stats.All.values()
                .stream()
                .filter(x -> x.IsShownOnTooltip() && x.statGroup().equals(statgroup))
                .collect(Collectors.toList())) {

            String str = stat.translate() + ": " + data.getUnit().MyStats.get(stat.GUID()).Value;

            if (stat.IsPercent()) {
                str += '%';
            }

            list.add(str);

        }
        int x = this.getTextStartX();
        int y = this.getTextStartY();

        int added = 0;

        for (int i = currentElement; i < list.size(); i++) {
            if (i > 0 && i < list.size() - 1) {
                String str = list.get(i);

                if (added < this.sizeY - 50) {
                    this.drawString(mc.fontRenderer, str, x, y, TextFormatting.YELLOW.getColor());
                    y += this.fontRenderer.FONT_HEIGHT + 1;
                    added += this.fontRenderer.FONT_HEIGHT + 1;
                }
            }
        }

        return list.size();

    }

    @Override
    public boolean mouseScrolled(double pMouseScrolled1) {
        this.currentElement -= pMouseScrolled1;
        this.currentElement = MathHelper.clamp(currentElement, 0, renderStats());
        return true;

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

    @Override
    public boolean mouseClicked(double X, double Y, int p_mouseClicked_5_) {
        if (super.mouseClicked(X, Y, p_mouseClicked_5_)) {
            return true;
        } else {
            // my stuff

            return true;
        }
    }

    @Nullable
    @Override
    public ITextComponent getCustomName() {
        return new TextComponentString("Stats Screen");
    }
}
