package com.robertx22.database.unique_items.swords;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackNatureDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.database.stats.stat_mods.percent.LifestealPercent;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleCriticalDamagePercent;
import com.robertx22.database.unique_items.bases.BaseUniqueSword;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class SwordNature extends BaseUniqueSword {
    public SwordNature() {

    }

    @Override
    public int Tier() {
        return 2;
    }

    @Override
    public String GUID() {
        return "swordnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AttackNatureDamageFlat(), new LifestealFlat(), new LifestealPercent(), new HealthRegenFlat(), new CrippleCriticalDamagePercent());
    }

    @Override
    public String locDescForLangFile() {
        return Styles.YELLOW + "Sword of the Nature Spirit";
    }

    @Override
    public String locNameForLangFile() {
        return "Forever lasting.";
    }
}
