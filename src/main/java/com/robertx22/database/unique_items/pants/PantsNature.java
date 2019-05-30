package com.robertx22.database.unique_items.pants;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.BaseTransferFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.database.stats.stat_mods.traits.conditionals.low_dodge.LowDodgeAddArmorFlat;
import com.robertx22.database.unique_items.bases.BaseUniquePants;
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
        return Arrays.asList(new LowDodgeAddArmorFlat(), new HealthFlat(), new NatureResistFlat(), new BaseTransferFlat(Elements.Fire, Elements.Nature), new CrippleLifeOnHitPercent());
    }

}
