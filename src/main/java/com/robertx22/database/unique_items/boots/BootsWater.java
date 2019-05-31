package com.robertx22.database.unique_items.boots;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.WaterSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.flat.weapon_damages.SwordDamageFlat;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleLifestealPercent;
import com.robertx22.database.stats.stat_mods.percent.offense.CriticalDamagePercent;
import com.robertx22.database.unique_items.bases.BaseUniqueBoots;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class BootsWater extends BaseUniqueBoots {

    public BootsWater() {

    }

    @Override
    public int Tier() {
        return 18;
    }

    @Override
    public String GUID() {
        return "bootswater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthFlat(), new SwordDamageFlat(), new WaterSpellToAttackFlat(), new CriticalDamagePercent(), new WaterResistFlat(), new CrippleLifestealPercent());

    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Ice Steps";
    }

    @Override
    public String locDescForLangFile() {
        return "Ice forms wherever I walk.";
    }
}
