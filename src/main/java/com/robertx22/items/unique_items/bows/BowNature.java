package com.robertx22.items.unique_items.bows;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.DexterityFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackNatureDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.NaturePeneFlat;
import com.robertx22.database.stats.stat_mods.multi.defense.CriticalHitMulti;
import com.robertx22.items.unique_items.bases.BaseUniqueBow;

import java.util.Arrays;
import java.util.List;

public class BowNature extends BaseUniqueBow {
    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AttackNatureDamageFlat(), new DexterityFlat(), new NaturePeneFlat(), new CriticalHitMulti());
    }

    @Override
    public String GUID() {
        return "bow_nature0";
    }

    @Override
    public int Tier() {
        return 10;
    }
}
