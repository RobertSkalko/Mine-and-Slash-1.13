package com.robertx22.database.stats;

import com.robertx22.database.IGUID;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.Words;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.interfaces.IAutoLocDesc;
import com.robertx22.uncommon.interfaces.IAutoLocName;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class Stat implements IGUID, IAutoLocName, IAutoLocDesc {

    public Stat() {
    }

    // 14 per 14

    static final int rows = 13;
    static final int spriteSize = 18;

    public int getSpriteX() {
        int placeX = this.iconSpriteNumber() % rows;
        return 1 + placeX * spriteSize;
    }

    public int getSpriteY() {
        int placeY = this.iconSpriteNumber() / rows;

        return 1 + placeY * spriteSize;
    }

    public int iconSpriteNumber() {
        return 0;
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".stat." + formattedGUID();
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + formattedGUID();
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Stats;
    }

    public int maximumValue = Integer.MAX_VALUE;

    public int minimumValue = Integer.MIN_VALUE;

    public abstract boolean IsPercent();

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

    public ITextComponent NameText(TooltipInfo info, StatModData data) {
        StatMod mod = data.getStatMod();
        Stat basestat = mod.GetBaseStat();

        ITextComponent str = basestat.locName();

        if (mod.Type().equals(StatTypes.Percent) && basestat.IsPercent()) {
            str.appendText(" ").appendSibling(Words.Percent.locName());
        }

        if (info.isSet == false) {
            return Styles.REDCOMP()
                    .appendSibling(new StringTextComponent(" * ").appendSibling(str)
                            .appendText(": "));
        } else {
            return Styles.GREENCOMP().appendSibling(str.appendText(": "));
        }
    }

    public ITextComponent NameAndValueText(TooltipInfo info, StatModData data) {

        float val = data.GetActualVal(info.level);

        String minusplus = val > 0 ? "+" : "";

        return NameText(info, data).appendText(minusplus + printValue(data, info.level));
    }

    public List<ITextComponent> getTooltipList(TooltipInfo info, StatModData data) {

        List<ITextComponent> list = new ArrayList<ITextComponent>();
        StatMod mod = data.getStatMod();
        Stat basestat = mod.GetBaseStat();
        ITextComponent text = NameAndValueText(info, data);

        if (mod.Type() == StatTypes.Flat) {

            if (basestat.IsPercent()) {
                text.appendText("%");
            }

        } else if (mod.Type() == StatTypes.Percent) {
            text.appendText("%");
        } else {
            text.appendText("% ").appendSibling(Words.Multi.locName());
        }

        if (Screen.hasShiftDown() && info.isSet == false) {

            StatModData min = StatModData.Load(data.getStatMod(), info.minmax.Min);
            StatModData max = StatModData.Load(data.getStatMod(), info.minmax.Max);

            ITextComponent extraInfo = Styles.GREENCOMP()
                    .appendSibling(new StringTextComponent(" (" + min.printValue(info.level) + " - " + max
                            .printValue(info.level) + ")"));

            text.appendSibling(extraInfo);
        }

        list.add(text);
        if (Screen.hasAltDown()) {
            list.add(Styles.BLUECOMP()
                    .appendText(" [")
                    .appendSibling(this.locDesc().appendText("]")));
        }
        return list;

    }

    public void CalcVal(StatData data, UnitData Source) {

        float finalValue = BaseFlat;

        if (ScalesToLevel()) {
            finalValue *= Source.getLevel();
        }

        finalValue += data.Flat;

        finalValue *= 1 + data.Percent / 100;

        finalValue *= 1 + data.Multi / 100;

        data.Value = MathHelper.clamp(finalValue, minimumValue, maximumValue);

    }

    public boolean IsShownOnTooltip() {
        return true;
    }

    public StatGroup statGroup() {
        return StatGroup.Misc;
    }

    public enum StatGroup {
        Main(Words.Main, 0),
        Misc(Words.Misc, 8),
        CoreStat(Words.Core_Stat, 5),
        SpellDamage(Words.Spell_Damage, 3),
        EleAttackDamage(Words.Elemental_Attack_Damage, 2),
        Defenses(Words.Defenses, 4),
        Penetration(Words.Penetration, 6),
        Damage(Words.Damage, 1),
        Regeneration(Words.Regeneration, 7);

        StatGroup(Words word, int place) {
            this.place = place;
            this.word = word;
        }

        public Words word;

        public final int width = 18;
        public final int height = 18;

        public int place = 0;

        public final int Y = 8;

        public int X() {
            return 25 + width * place;
        }
    }

}
