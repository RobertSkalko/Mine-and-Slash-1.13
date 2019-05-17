package com.robertx22.database.stats.stat_types.spell_buff_traits.base;

import com.robertx22.Styles;
import com.robertx22.database.MinMax;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IStatEffects;
import com.robertx22.uncommon.interfaces.ITrait;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

public abstract class SpellBuffTrait extends Stat implements IStatEffects, ITrait {

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
    public ITextComponent Description() {
        return CLOC.tooltip(this.GUID().toLowerCase().replaceAll(" ", "_"));
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
        return Styles.GREENCOMP()
                .appendSibling(new TextComponentString(" * ").appendSibling(basestat.locName()));
    }

    @Override
    public List<ITextComponent> getTooltipList(MinMax minmax, StatModData data, int level,
                                               boolean IsNotSet) {
        List<ITextComponent> list = new ArrayList<ITextComponent>();
        StatMod mod = data.GetBaseMod();
        Stat basestat = mod.GetBaseStat();
        ITextComponent text = new TextComponentString("");

        text = TraitText(data);
        SpellBuffTrait trait = (SpellBuffTrait) basestat;

        list.add(text);

        if (GuiScreen.isShiftKeyDown()) {
            list.add(Styles.GRAYCOMP()
                    .appendSibling(new TextComponentString(" ").appendSibling(trait.Description())));
        }

        return list;
    }
}
