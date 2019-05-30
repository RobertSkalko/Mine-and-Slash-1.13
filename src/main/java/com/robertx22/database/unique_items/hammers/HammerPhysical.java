package com.robertx22.database.unique_items.hammers;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorPeneFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.database.stats.stat_mods.percent.less.LessManaOnHitPercent;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.database.stats.stat_mods.percent.offense.CriticalDamagePercent;
import com.robertx22.database.stats.stat_mods.percent.offense.CriticalHitPercent;
import com.robertx22.database.unique_items.bases.BaseUniqueHammer;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class HammerPhysical extends BaseUniqueHammer {
    public HammerPhysical() {

    }

    @Override
    public int Tier() {
        return 8;
    }

    @Override
    public String GUID() {
        return "hammerphysical0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new PhysicalDamageFlat(), new ArmorPeneFlat(), new CriticalHitPercent(), new CriticalDamagePercent(), new CrippleLifeOnHitPercent(), new LessManaOnHitPercent());
    }

    @Override
    public String locDescForLangFile() {
        return Styles.YELLOW + "Mountain Breaker";
    }

    @Override
    public String locNameForLangFile() {
        return "Not even mountains can bar my path!";
    }
}