package com.robertx22.database.unique_items.bracelets;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellNatureDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.percent.HealthRegenPercent;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleCriticalDamagePercent;
import com.robertx22.database.unique_items.bases.BaseUniqueBracelet;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class BraceletNature extends BaseUniqueBracelet {

    public BraceletNature() {

    }

    @Override
    public int Tier() {
        return 2;
    }

    @Override
    public String GUID() {
        return "braceletnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new SpellNatureDamageFlat(), new NatureResistFlat(), new HealthFlat(), new HealthRegenPercent(), new CrippleCriticalDamagePercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Thorn Bracers";
    }

    @Override
    public String locDescForLangFile() {
        return "Fools fight for treasure, but I keep on living.";
    }
}
