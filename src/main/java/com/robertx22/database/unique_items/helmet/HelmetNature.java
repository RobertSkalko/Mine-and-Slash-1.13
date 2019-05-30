package com.robertx22.database.unique_items.helmet;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellNatureDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.less.LessManaOnHitPercent;
import com.robertx22.database.stats.stat_mods.traits.conditionals.low_dodge.LowDodgeAddArmorFlat;
import com.robertx22.database.unique_items.bases.BaseUniqueHelmet;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class HelmetNature extends BaseUniqueHelmet {

    public HelmetNature() {

    }

    @Override
    public int Tier() {
        return 19;
    }

    @Override
    public String GUID() {
        return "helmetnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new LowDodgeAddArmorFlat(), new SpellNatureDamageFlat(), new HealthRegenFlat(), new HealthFlat(), new NatureResistFlat(), new LessManaOnHitPercent());
    }

    @Override
    public String locDescForLangFile() {
        return Styles.YELLOW + "Leaf Covering";
    }

    @Override
    public String locNameForLangFile() {
        return "Nature comes to my aid.";
    }
}