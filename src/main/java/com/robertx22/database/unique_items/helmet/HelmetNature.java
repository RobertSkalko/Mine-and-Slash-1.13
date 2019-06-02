package com.robertx22.database.unique_items.helmet;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.database.stats.stat_mods.percent.less.LessManaOnHitPercent;
import com.robertx22.database.stats.stat_mods.traits.conditionals.low_dodge.LowDodgeAddArmorFlat;
import com.robertx22.database.unique_items.bases.BaseUniqueHelmet;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.enumclasses.Elements;

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
        return Arrays.asList(new LowDodgeAddArmorFlat(), new ElementalSpellDamageFlat(Elements.Nature), new HealthRegenFlat(), new HealthFlat(), new ElementalResistFlat(Elements.Nature), new LessManaOnHitPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Leaf Covering";
    }

    @Override
    public String locDescForLangFile() {
        return "Nature comes to my aid.";
    }
}