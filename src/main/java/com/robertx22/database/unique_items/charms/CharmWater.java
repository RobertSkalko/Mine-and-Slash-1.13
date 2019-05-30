package com.robertx22.database.unique_items.charms;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.conversions.ManaToEnergyConvFlat;
import com.robertx22.database.stats.stat_mods.percent.much_less.CrippleDodgePercent;
import com.robertx22.database.stats.stat_mods.percent.pene.WaterPenePercent;
import com.robertx22.database.unique_items.bases.BaseUniqueCharm;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class CharmWater extends BaseUniqueCharm {

    public CharmWater() {

    }

    @Override
    public int Tier() {
        return 18;
    }

    @Override
    public String GUID() {
        return "charmwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ManaToEnergyConvFlat(), new ArmorFlat(), new WaterPeneFlat(), new WaterPenePercent(), new WaterResistFlat(), new CrippleDodgePercent());
    }

    @Override
    public String locDescForLangFile() {
        return Styles.YELLOW + "Charm of the River Dragon";
    }

    @Override
    public String locNameForLangFile() {
        return "My path cannot be stopped.";
    }
}
