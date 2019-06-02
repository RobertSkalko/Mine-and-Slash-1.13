package com.robertx22.database.unique_items.staffs;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalAttackDamageFlat;
import com.robertx22.database.stats.stat_mods.percent.pene.WaterPenePercent;
import com.robertx22.database.unique_items.bases.BaseUniqueStaff;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class StaffWater extends BaseUniqueStaff {

    public StaffWater() {

    }

    @Override
    public int Tier() {
        return 5;
    }

    @Override
    public String GUID() {
        return "uniquestaffwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(Elements.Water), new CriticalDamageFlat(), new CriticalHitFlat(), new ElementalPeneFlat(Elements.Water), new WaterPenePercent());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Staff of Permafrost";
    }

    @Override
    public String locDescForLangFile() {
        return "What is frozen is as good as dead.";
    }
}
