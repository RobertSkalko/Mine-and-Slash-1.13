package com.robertx22.database.unique_items.bows;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.WisdomFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackWaterDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.WaterSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.database.unique_items.bases.BaseUniqueBow;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class BowWater extends BaseUniqueBow {
    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AttackWaterDamageFlat(), new WisdomFlat(), new WaterSpellToAttackFlat(), new WaterPeneFlat());
    }

    @Override
    public String GUID() {
        return "bow_water0";
    }

    @Override
    public int Tier() {
        return 10;
    }

    @Override
    public String locDescForLangFile() {
        return Styles.YELLOW + "Bow of Water Affinity";
    }

    @Override
    public String locNameForLangFile() {
        return "Aim steady, imbue with Frost!";
    }
}
