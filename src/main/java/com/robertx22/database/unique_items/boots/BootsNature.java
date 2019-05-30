package com.robertx22.database.unique_items.boots;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.BaseTransferFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.HealthPercent;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleDodgePercent;
import com.robertx22.database.unique_items.bases.BaseUniqueBoots;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class BootsNature extends BaseUniqueBoots {

    public BootsNature() {

    }

    @Override
    public int Tier() {
        return 7;
    }

    @Override
    public String GUID() {
        return "bootsnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthFlat(), new HealthPercent(), new HealthRegenFlat(), new BaseTransferFlat(Elements.Thunder, Elements.Nature), new NatureResistFlat(), new CrippleDodgePercent());

    }

}
