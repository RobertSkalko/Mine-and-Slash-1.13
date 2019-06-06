package com.robertx22.database.stats.stat_types;

import com.robertx22.database.MinMax;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.Trait;
import com.robertx22.database.stats.stat_types.traits.major_arcana.INameSuffix;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.interfaces.ITrait;
import com.robertx22.uncommon.interfaces.IWeighted;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTrait extends Stat implements ITrait, IWeighted {

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public boolean IsShownOnTooltip() {
        return false;
    }

    @Override
    public void CalcVal(StatData data, EntityData.UnitData Source) {

        if (data.Flat > 0) {
            data.Value = 1;
        } else {
            data.Value = 0;

        }

    }

    public ITextComponent TraitText(StatModData data) {
        StatMod mod = data.getStatMod();
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
        StatMod mod = data.getStatMod();
        Stat basestat = mod.GetBaseStat();
        ITextComponent text = new TextComponentString("");

        text = TraitText(data);

        list.add(text);

        if (GuiScreen.isAltKeyDown()) {

            list.add(new TextComponentString(TextFormatting.BLUE + "[").appendSibling(this
                    .locDesc()).appendText("]"));

            if (basestat instanceof Trait) {
                Trait trait = (Trait) basestat;

                for (StatModData motdata : trait.getStatsMods()) {
                    TooltipInfo traitinfo = info;
                    traitinfo.minmax = new MinMax(trait.percent(), trait.percent());
                    list.addAll(motdata.GetTooltipString(info));
                }
            }
        }

        return list;
    }
}
