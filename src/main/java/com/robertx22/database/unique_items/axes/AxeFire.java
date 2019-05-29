package com.robertx22.database.unique_items.axes;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackFireDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.FirePeneFlat;
import com.robertx22.database.stats.stat_mods.percent.offense.CriticalDamagePercent;
import com.robertx22.database.stats.stat_mods.percent.offense.CriticalHitPercent;
import com.robertx22.database.unique_items.bases.BaseUniqueAxe;

import java.util.Arrays;
import java.util.List;

public class AxeFire extends BaseUniqueAxe {
    public AxeFire() {

    }

    @Override
    public int Tier() {
        return 3;
    }

    @Override
    public String GUID() {
        return "axefire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AttackFireDamageFlat(), new CriticalHitPercent(), new CriticalDamagePercent(), new FirePeneFlat());
    }

}
