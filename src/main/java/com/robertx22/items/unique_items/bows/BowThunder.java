package com.robertx22.items.unique_items.bows;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.corestats.IntelligenceFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackThunderDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.ThunderSpellToAttackFlat;
import com.robertx22.items.unique_items.bases.BaseUniqueBow;

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
}
