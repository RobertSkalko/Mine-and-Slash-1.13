package com.robertx22.database.unique_items.bows;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.DexterityFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.NaturePeneFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalAttackDamageFlat;
import com.robertx22.database.stats.stat_mods.multi.defense.CriticalHitMulti;
import com.robertx22.database.unique_items.bases.BaseUniqueBow;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class BowNature extends BaseUniqueBow {
    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(Elements.Nature), new DexterityFlat(), new NaturePeneFlat(), new CriticalHitMulti());
    }

    @Override
    public String GUID() {
        return "bow_nature0";
    }

    @Override
    public int Tier() {
        return 10;
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Bow of Nature Affinity";
    }

    @Override
    public String locDescForLangFile() {
        return "Aim steady, imbue with poison!";
    }
}
