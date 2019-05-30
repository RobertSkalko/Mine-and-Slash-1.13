package com.robertx22.database.unique_items.bows;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.IntelligenceFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackThunderDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.ThunderSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.database.unique_items.bases.BaseUniqueBow;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class BowThunder extends BaseUniqueBow {
    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AttackThunderDamageFlat(), new IntelligenceFlat(), new ThunderSpellToAttackFlat(), new CriticalDamageFlat());
    }

    @Override
    public String GUID() {
        return "bow_thunder0";
    }

    @Override
    public int Tier() {
        return 10;
    }

    @Override
    public String locDescForLangFile() {
        return Styles.YELLOW + "Bow of Thunder Affinity";
    }

    @Override
    public String locNameForLangFile() {
        return "Aim steady, imbue with Lightning!";
    }
}
