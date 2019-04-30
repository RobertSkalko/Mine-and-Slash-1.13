package com.robertx22.database.stats;

import com.robertx22.Styles;
import com.robertx22.database.IGUID;
import com.robertx22.database.MinMax;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.interfaces.IStatEffect;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class Stat implements IGUID {

    public Stat() {
    }

    @Override
    public String GUID() {
        return Guid();
    }

    public abstract String unlocString();

    public ITextComponent localizedString() {
        return CLOC.stat(unlocString().toLowerCase().replaceAll(" ", "_"));
    }

    public int MaximumPercent = 0;

    public int MinimumAmount = 0;

    public boolean hasMinimumAmount = true;

    public int StatMinimum = 0;

    public abstract boolean IsPercent();

    public abstract String Guid();

    public abstract boolean ScalesToLevel();

    public abstract Elements Element();

    public int BaseFlat = 0;

    private String printValue(StatModData data, int level) {

        float val = data.GetActualVal(level);

        DecimalFormat format = new DecimalFormat();

        if (val < 10) {
            format.setMaximumFractionDigits(1);

            return format.format(val);

        } else {

            int intval = (int) val;
            return intval + "";

        }

    }

    public ITextComponent NameText(boolean IsSet, StatModData data) {
        StatMod mod = data.GetBaseMod();
        Stat basestat = mod.GetBaseStat();

        ITextComponent str = basestat.localizedString();

        if (mod.Type().equals(StatTypes.Percent) && basestat.IsPercent()) {
            str.appendText(" ").appendSibling(CLOC.word("percent"));
        }

        if (IsSet) {
            return new TextComponentString(" * ").appendSibling(str)
                    .appendText(": ")
                    .setStyle(Styles.RED);
        } else {
            return str.appendText(": ").setStyle(Styles.RED);
        }
    }

    public ITextComponent NameAndValueText(StatModData data, int level, boolean IsSet) {

        float val = data.GetActualVal(level);

        String minusplus = val > 0 ? "+" : "";

        return NameText(IsSet, data).appendText(minusplus + printValue(data, level));
    }

    public List<ITextComponent> getTooltipList(MinMax minmax, StatModData data, int level,
                                               boolean IsNotSet) {

        List<ITextComponent> list = new ArrayList<ITextComponent>();
        StatMod mod = data.GetBaseMod();
        Stat basestat = mod.GetBaseStat();
        ITextComponent text = NameAndValueText(data, level, IsNotSet);

        if (mod.Type() == StatTypes.Flat) {

            if (basestat.IsPercent()) {
                text.appendText("%");
            }

        } else if (mod.Type() == StatTypes.Percent) {
            text.appendText("%");
        } else {
            text.appendText("% ").appendSibling(CLOC.word("multi"));
        }

        if (GuiScreen.isShiftKeyDown() && IsNotSet) {

            StatModData min = StatModData.Load(data.GetBaseMod(), minmax.Min);
            StatModData max = StatModData.Load(data.GetBaseMod(), minmax.Max);

            text.appendSibling(new TextComponentString(" (" + min.printValue(level) + " - " + max
                    .printValue(level) + ")")).setStyle(Styles.BLUE);
        }

        list.add(text);

        return list;

    }

    public int CalcVal(StatData data, UnitData Source) {

        float finalValue = BaseFlat;

        finalValue += StatMinimum;

        if (ScalesToLevel()) {
            finalValue *= Source.getLevel();
        }

        finalValue += data.Flat;

        finalValue *= 1 + data.Percent / 100;

        finalValue *= 1 + data.Multi / 100;

        if (hasMinimumAmount && finalValue < this.MinimumAmount) {
            finalValue = this.MinimumAmount;
        }

        if (this.IsPercent() && MaximumPercent > 0 && finalValue > MaximumPercent) {
            finalValue = MaximumPercent;
        }

        data.Value = finalValue;

        return (int) finalValue;

    }

    public ArrayList<IStatEffect> Effects;

    public boolean IsShownOnTooltip() {
        return true;
    }

}
