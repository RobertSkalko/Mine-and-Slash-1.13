package com.robertx22.mine_and_slash.database.affixes.suffixes.offense;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorPeneFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.ArmorPenePercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;

import java.util.Arrays;
import java.util.List;

public class OfRockPiercing extends Suffix {

    public OfRockPiercing() {
    }

    @Override
    public String GUID() {
        return "OfRockPiercing";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new ArmorPeneFlat(), new PhysicalDamagePercent(), new ArmorPenePercent());
    }

    @Override
    public int Weight() {
        return IWeighted.LegendaryWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.weaponsOnly(), LevelRequirement.fromLVL50());
    }

    @Override
    public String locNameForLangFile() {
        return "Of Rock Piercing";
    }
}