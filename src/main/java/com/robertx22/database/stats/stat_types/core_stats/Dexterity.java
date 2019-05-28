package com.robertx22.database.stats.stat_types.core_stats;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stats.stat_mods.flat.DodgeFlat;

import java.util.Arrays;
import java.util.List;

public class Dexterity extends BaseCoreStat {

    @Override
    public String statDescription() {
        return "Increases Crit Hit, Dodge and Armor";
    }

    @Override
    public String GUID() {
        return "Dexterity";
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(new CriticalHitFlat(), new DodgeFlat(), new ArmorFlat());
    }
}
