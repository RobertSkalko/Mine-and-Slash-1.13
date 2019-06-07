package com.robertx22;

import com.robertx22.database.stats.IUsableStat;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.UnknownStat;
import com.robertx22.db_lists.Stats;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class StatGUI extends GuiScreen {

    Stat.StatGroup statgroup = Stat.StatGroup.Main;
    int currentElement = 0;
    HashMap<String, List<Stat>> statmap = new HashMap<>();

    public StatGUI() {

        genStatList();

    }

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

        mc.getTextureManager().bindTexture(texture);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(mc.mainWindow.getScaledWidth() / 2 - this.sizeX / 2, mc.mainWindow
                .getScaledHeight() / 2 - this.sizeY / 2, 0, 0, sizeX, sizeY);

        renderStats();

    }

    private int getGUIStartX() {

        return (int) (mc.mainWindow.getScaledWidth() / 2 - this.sizeX / 2);
    }

    private int getGUIStartY() {

        return (int) (mc.mainWindow.getScaledHeight() / 2 - this.sizeY / 2);
    }

    private int getTextStartX() {

        return (int) (mc.mainWindow.getScaledWidth() / 2 - this.sizeX / 2 + 35);
    }

    private int getTextStartY() {

        return (int) (mc.mainWindow.getScaledHeight() / 2 - this.sizeY / 2 + 40);
    }

    private String getStatString(Stat stat, EntityData.UnitData data) {

        String str = stat.translate() + ": " + data.getUnit().MyStats.get(stat.GUID())
                .formattedValue();

        if (stat.IsPercent()) {
            str += '%';
        }

        if (stat instanceof IUsableStat) {
            IUsableStat usable = (IUsableStat) stat;

            String value = formattedValue(usable.GetUsableValue(data.getLevel(), (int) data
                    .getUnit().MyStats.get(stat.GUID()).Value) * 100);

            str += " (" + value + "%)";

        }
        return str;

    }

    private List<Stat> getList() {

        List<Stat> list = new ArrayList<>();

        for (Map.Entry<String, List<Stat>> entry : statmap.entrySet()) {
            for (Stat stat : entry.getValue()) {
                list.add(stat);
            }
            list.add(new UnknownStat());
        }

        return list;

    }

    private int drawAndIncreaseSpacing(int x, int y, String str) {
        this.drawString(mc.fontRenderer, str, x, y, TextFormatting.GOLD.getColor());
        return this.getHeightSpacing();

    }

    private int renderStats() {

        List<Stat> list = getList();

        int x = this.getTextStartX();
        int y = this.getTextStartY();

        int added = 0;

        added += this.drawAndIncreaseSpacing(x, y + added, this.statgroup.word.translate() + ": ");
        added += this.drawAndIncreaseSpacing(x, y + added, "");

        EntityData.UnitData data = Load.Unit(mc.player);

        for (int i = currentElement; i < list.size(); i++) {
            if (i > -1) { // or scrolling crashes

                if (list.get(i) instanceof UnknownStat) {
                    added += this.drawAndIncreaseSpacing(x, y + added, "");
                    continue;
                }

                Stat stat = list.get(i);
                String str = this.getStatString(stat, data);

                if (added < this.sizeY - 50) {
                    // HERE
                    mc.getTextureManager().bindTexture(stat.statIcon());
                    GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                    drawTexturedModalRect(x - 50, y + added, 0, 0, 16, 16);

                    added += this.drawAndIncreaseSpacing(x, y + added, str);

                }
            }

        }

        return list.size();

    }

    private int getHeightSpacing() {
        return 18;
    }

    public static String formattedValue(float val) {

        DecimalFormat format = new DecimalFormat();

        if (Math.abs(val) < 10) {
            format.setMaximumFractionDigits(1);

            return format.format(val);

        } else {
            int intval = (int) val;
            return intval + "";
        }

    }

    @Override
    public boolean mouseScrolled(double pMouseScrolled1) {
        this.currentElement -= pMouseScrolled1;
        this.currentElement = MathHelper.clamp(currentElement, 0, renderStats());

        return true;

    }

    void genStatList() {

        this.statmap = new HashMap<>();

        List<Stat> statlist = Stats.All.values()
                .stream()
                .filter(stat -> stat.IsShownOnTooltip() && stat.statGroup()
                        .equals(statgroup))
                .collect(Collectors.toList());

        Collections.sort(statlist, Comparator.comparing(stat -> stat.GUID()));

        List<Stat> misc = new ArrayList<>();

        for (Stat stat : statlist) {
            List<Stat> same = statlist.stream()
                    .filter(x -> x.getClass() == stat.getClass())
                    .collect(Collectors.toList());

            if (same.size() > 1) {
                statmap.put(stat.getClass().getName(), same);
            } else {
                misc.add(stat);
            }
        }
        statmap.put("misc", misc);

    }

    @Override
    public boolean mouseClicked(double X, double Y, int idk) {
        if (super.mouseClicked(X, Y, idk)) {
            return true;
        } else {
            // my stuff

            for (Stat.StatGroup group : Stat.StatGroup.values()) {

                int x = group.X() + this.getGUIStartX();
                int y = group.Y + this.getGUIStartY();

                if (isInRect(x, y, group.width, group.height, (int) X, (int) Y)) {

                    this.currentElement = 0;
                    this.statgroup = group;
                    genStatList();
                }

            }

            return true;
        }
    }

}
