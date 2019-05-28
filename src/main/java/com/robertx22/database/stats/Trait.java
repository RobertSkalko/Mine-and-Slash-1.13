package com.robertx22.database.stats;

import com.robertx22.database.MinMax;
import com.robertx22.database.stats.stat_types.traits.major_arcana.INameSuffix;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IAffectsOtherStats;
import com.robertx22.uncommon.interfaces.ITrait;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public abstract class Trait extends Stat implements IAffectsOtherStats, ITrait {

    @Override
    public void TryAffectOtherStats(UnitData unit) {
        if (this.condition(unit)) {
            for (StatModData mod : getStatsMods()) {
                mod.useOnPlayer(unit);
            }
        }

    }

    @Override
    public String statDescription() {
        return "";
    }

    @Override
    public ITextComponent Description() {
        return new TextComponentString("");
    }

    // override if it has a condition
    public boolean condition(UnitData unit) {
        return true;
    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public Elements Element() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public int CalcVal(StatData data, UnitData Source) {

        if (data.Flat > 0) {
            data.Value = 1;

            return 1;

        } else {
            data.Value = 0;
            return 0;
        }

    }

    public ITextComponent TraitText(StatModData data) {
        StatMod mod = data.GetBaseMod();
        Stat basestat = mod.GetBaseStat();
        ITextComponent comp = Styles.GREENCOMP()
                .appendSibling(new TextComponentString(" * ").appendSibling(basestat.locName()));

        if (basestat instanceof INameSuffix) {
            INameSuffix suffix = (INameSuffix) basestat;
            comp.appendText(TextFormatting.LIGHT_PURPLE + " * (");
            comp.appendSibling(suffix.locSuffix().appendText(") *"));
        }

        return comp;

    }

    @Override
    public List<ITextComponent> getTooltipList(TooltipInfo info, StatModData data) {
        List<ITextComponent> list = new ArrayList<ITextComponent>();
        StatMod mod = data.GetBaseMod();
        Stat basestat = mod.GetBaseStat();
        ITextComponent text = new TextComponentString("");

        text = TraitText(data);
        Trait trait = (Trait) basestat;

        if (GuiScreen.isShiftKeyDown()) {
            text.appendSibling(Styles.GRAYCOMP())
                    .appendText(" ")
                    .appendSibling(trait.Description());
        }

        list.add(text);

        if (GuiScreen.isShiftKeyDown()) {

            for (StatModData motdata : trait.getStatsMods()) {

                TooltipInfo traitinfo = info;
                traitinfo.minmax = new MinMax(trait.percent(), trait.percent());

                list.addAll(motdata.GetTooltipString(info));
            }

        }

        return list;
    }

}
