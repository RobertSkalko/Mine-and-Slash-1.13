package com.robertx22.database.unique_items.swords;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.less.LessHealthRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.database.stats.stat_mods.percent.offense.MajorCriticalDamagePercent;
import com.robertx22.database.stats.stat_mods.percent.offense.MajorCriticalHitPercent;
import com.robertx22.database.unique_items.bases.BaseUniqueSword;
import com.robertx22.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class SwordPhysical extends BaseUniqueSword {
    public SwordPhysical() {

    }

    @Override
    public int Tier() {
        return 9;
    }

    @Override
    public String GUID() {
        return "swordphysical0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new PhysicalDamageFlat(), new MajorCriticalHitPercent(), new MajorCriticalDamagePercent(), new LessHealthRegenFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Blade of Doom";
    }

    @Override
    public String locDescForLangFile() {
        return "Toughest Opponents fear no pain.";
    }
}
