package com.robertx22.database.unique_items.axes;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackThunderDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.database.stats.stat_mods.percent.offense.CriticalDamagePercent;
import com.robertx22.database.stats.stat_mods.percent.offense.CriticalHitPercent;
import com.robertx22.database.unique_items.bases.BaseUniqueAxe;

import java.util.Arrays;
import java.util.List;

public class AxeThunder extends BaseUniqueAxe {
    public AxeThunder() {

    }

    @Override
    public int Tier() {
        return 11;
    }

    @Override
    public String GUID() {
        return "axethunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AttackThunderDamageFlat(), new CriticalHitFlat(), new CriticalHitPercent(), new CriticalDamagePercent(), new CrippleLifeOnHitPercent());
    }

}
