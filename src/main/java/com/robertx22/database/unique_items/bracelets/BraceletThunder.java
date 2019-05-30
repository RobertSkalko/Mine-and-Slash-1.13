package com.robertx22.database.unique_items.bracelets;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellThunderDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleLifestealPercent;
import com.robertx22.database.stats.stat_mods.percent.offense.CriticalDamagePercent;
import com.robertx22.database.stats.stat_mods.percent.offense.CriticalHitPercent;
import com.robertx22.database.unique_items.bases.BaseUniqueBracelet;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class BraceletThunder extends BaseUniqueBracelet {

    public BraceletThunder() {

    }

    @Override
    public int Tier() {
        return 8;
    }

    @Override
    public String GUID() {
        return "braceletthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new SpellThunderDamageFlat(), new CriticalHitPercent(), new CriticalDamagePercent(), new CriticalHitFlat(), new CriticalDamageFlat(), new CrippleLifestealPercent());
    }

    @Override
    public String locDescForLangFile() {
        return Styles.YELLOW + "Thunder Bracers";
    }

    @Override
    public String locNameForLangFile() {
        return "Dedication brings unparalleled might.";
    }
}
