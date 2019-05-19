package com.robertx22.database.affixes.suffixes.offense;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorPeneFlat;
import com.robertx22.database.stats.stat_mods.percent.ArmorPenePercent;
import com.robertx22.database.stats.stat_mods.percent.PhysicalDamagePercent;
import com.robertx22.uncommon.interfaces.IWeighted;

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

}