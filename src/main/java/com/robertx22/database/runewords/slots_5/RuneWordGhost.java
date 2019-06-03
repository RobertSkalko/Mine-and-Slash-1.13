package com.robertx22.database.runewords.slots_5;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.AllTraitMods;
import com.robertx22.database.stats.stat_mods.flat.DodgeFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.database.stats.stat_mods.percent.DodgePercent;
import com.robertx22.database.stats.stat_types.spell_buff_traits.GhostProjectileTrait;
import com.robertx22.items.runes.BerItem;
import com.robertx22.items.runes.CenItem;
import com.robertx22.items.runes.VohItem;
import com.robertx22.items.runes.XahItem;
import com.robertx22.items.runes.base.BaseRuneItem;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class RuneWordGhost extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new AllTraitMods(new GhostProjectileTrait()), new DodgeFlat(), new DodgePercent(), new ElementalResistFlat(Elements.Nature));
    }

    @Override
    public String GUID() {
        return "Ghost";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new VohItem(0), new XahItem(0), new BerItem(0), new XahItem(0), new CenItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Ghost";
    }
}
