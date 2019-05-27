package com.robertx22.database.unique_items.bows;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.StrengthFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackFireDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.FireSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.database.unique_items.bases.BaseUniqueBow;

import java.util.Arrays;
import java.util.List;

public class BowFire extends BaseUniqueBow {
    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AttackFireDamageFlat(), new StrengthFlat(), new FireSpellToAttackFlat(), new LifestealFlat());
    }

    @Override
    public String GUID() {
        return "bow_fire0";
    }

    @Override
    public int Tier() {
        return 10;
    }
}
