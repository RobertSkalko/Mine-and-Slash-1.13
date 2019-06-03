package com.robertx22.database.unique_items.pants;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.AllTraitMods;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalTransferFlat;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.database.stats.stat_types.traits.low_dodge.LowDodgeAddArmor;
import com.robertx22.database.unique_items.bases.BaseUniquePants;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class PantsNature extends BaseUniquePants {

    public PantsNature() {

    }

    @Override
    public int Tier() {
        return 14;
    }

    @Override
    public String GUID() {
        return "pantsnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AllTraitMods(new LowDodgeAddArmor()), new HealthFlat(), new ElementalResistFlat(Elements.Nature), new ElementalTransferFlat(Elements.Fire, Elements.Nature), new CrippleLifeOnHitPercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Rooted Leggings";
    }

    @Override
    public String locDescForLangFile() {
        return "Embrace my roots.";
    }
}
