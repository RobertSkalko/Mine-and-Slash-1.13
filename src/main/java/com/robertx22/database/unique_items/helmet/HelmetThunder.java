package com.robertx22.database.unique_items.helmet;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.ThunderSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.percent.less.LessLifeOnHitPercent;
import com.robertx22.database.stats.stat_mods.traits.conditionals.low_dodge.LowDodgeAddCritHitFlat;
import com.robertx22.database.unique_items.bases.BaseUniqueHelmet;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class HelmetThunder extends BaseUniqueHelmet {

    public HelmetThunder() {

    }

    @Override
    public int Tier() {
        return 18;
    }

    @Override
    public String GUID() {
        return "helmetthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new LowDodgeAddCritHitFlat(), new ThunderSpellToAttackFlat(), new EnergyRegenFlat(), new HealthFlat(), new ArmorFlat(), new LessLifeOnHitPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Thunder Atronach Helmet";
    }

    @Override
    public String locDescForLangFile() {
        return "I see sparks all around me.";
    }
}