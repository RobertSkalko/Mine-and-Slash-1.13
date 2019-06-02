package com.robertx22.database.unique_items.helmet;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellWaterDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.EnergyRegenPercent;
import com.robertx22.database.stats.stat_mods.percent.less.LessCriticalHitPercent;
import com.robertx22.database.unique_items.bases.BaseUniqueHelmet;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class HelmetWater extends BaseUniqueHelmet {

    public HelmetWater() {

    }

    @Override
    public int Tier() {
        return 12;
    }

    @Override
    public String GUID() {
        return "helmetwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new SpellWaterDamageFlat(), new ManaFlat(), new ManaRegenFlat(), new EnergyRegenPercent(), new ElementalResistFlat(Elements.Water), new LessCriticalHitPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Ice Seer Eyes";
    }

    @Override
    public String locDescForLangFile() {
        return "What mortals can't see belongs to me.";
    }
}