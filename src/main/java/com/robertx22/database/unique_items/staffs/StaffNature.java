package com.robertx22.database.unique_items.staffs;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackNatureDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.NaturePeneFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.pene.NaturePenePercent;
import com.robertx22.database.unique_items.bases.BaseUniqueStaff;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class StaffNature extends BaseUniqueStaff {

    public StaffNature() {

    }

    @Override
    public int Tier() {
        return 5;
    }

    @Override
    public String GUID() {
        return "uniquestaffnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AttackNatureDamageFlat(), new NaturePeneFlat(), new NaturePenePercent(), new HealthRegenFlat());
    }

    @Override
    public String locDescForLangFile() {
        return Styles.YELLOW + "Worldbreaker Staff";
    }

    @Override
    public String locNameForLangFile() {
        return "Won't break? Smash harder!";
    }
}
