package com.robertx22.database.unique_items.helmet;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.MajorArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.less.LessCriticalHitPercent;
import com.robertx22.database.stats.stat_mods.traits.conditionals.low_dodge.LowDodgeAddArmorFlat;
import com.robertx22.database.unique_items.bases.BaseUniqueHelmet;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class HelmetFire extends BaseUniqueHelmet {

    public HelmetFire() {

    }

    @Override
    public int Tier() {
        return 17;
    }

    @Override
    public String GUID() {
        return "helmetfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new LowDodgeAddArmorFlat(), new SpellFireDamageFlat(), new EnergyRegenFlat(), new MajorArmorFlat(), new FireResistFlat(), new LessCriticalHitPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Flame Atronach Helmet";
    }

    @Override
    public String locDescForLangFile() {
        return "I see flames all around me.";
    }
}