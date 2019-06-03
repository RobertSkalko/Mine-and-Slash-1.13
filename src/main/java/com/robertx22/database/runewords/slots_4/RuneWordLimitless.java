package com.robertx22.database.runewords.slots_4;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.AllTraitMods;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.DodgePercent;
import com.robertx22.database.stats.stat_types.spell_buff_traits.BuffEnergyRegenTrait;
import com.robertx22.items.runes.BerItem;
import com.robertx22.items.runes.ItaItem;
import com.robertx22.items.runes.VohItem;
import com.robertx22.items.runes.XahItem;
import com.robertx22.items.runes.base.BaseRuneItem;

import java.util.Arrays;
import java.util.List;

public class RuneWordLimitless extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new AllTraitMods(new BuffEnergyRegenTrait()), new EnergyRegenFlat(), new DodgePercent());
    }

    @Override
    public String GUID() {
        return "Limitless";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new VohItem(0), new ItaItem(0), new BerItem(0), new XahItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Limitless";
    }
}
