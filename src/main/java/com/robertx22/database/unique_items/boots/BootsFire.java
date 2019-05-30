package com.robertx22.database.unique_items.boots;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.FireSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.flat.weapon_damages.AxeDamageFlat;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.database.stats.stat_mods.percent.offense.CriticalDamagePercent;
import com.robertx22.database.unique_items.bases.BaseUniqueBoots;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class BootsFire extends BaseUniqueBoots {

    public BootsFire() {

    }

    @Override
    public int Tier() {
        return 18;
    }

    @Override
    public String GUID() {
        return "bootsfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthFlat(), new AxeDamageFlat(), new FireSpellToAttackFlat(), new CriticalDamagePercent(), new CrippleLifeOnHitPercent());

    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Flaming Steps";
    }

    @Override
    public String locDescForLangFile() {
        return "Fire brews wherever I walk.";
    }
}
