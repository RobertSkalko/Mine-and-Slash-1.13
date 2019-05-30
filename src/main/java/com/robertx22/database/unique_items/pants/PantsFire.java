package com.robertx22.database.unique_items.pants;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.BaseTransferFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.database.stats.stat_mods.traits.conditionals.low_dodge.LowDodgeAddCritHitFlat;
import com.robertx22.database.unique_items.bases.BaseUniquePants;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class PantsFire extends BaseUniquePants {

    public PantsFire() {

    }

    @Override
    public int Tier() {
        return 15;
    }

    @Override
    public String GUID() {
        return "pantsfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new LowDodgeAddCritHitFlat(), new HealthFlat(), new FireResistFlat(), new ArmorFlat(), new BaseTransferFlat(Elements.Nature, Elements.Fire), new CrippleLifeOnHitPercent());
    }

}
