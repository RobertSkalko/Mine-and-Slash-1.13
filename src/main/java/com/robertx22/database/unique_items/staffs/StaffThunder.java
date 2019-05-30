package com.robertx22.database.unique_items.staffs;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.DodgeFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.attack_dmg.AttackThunderDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaOnHitFlat;
import com.robertx22.database.stats.stat_mods.percent.pene.ThunderPenePercent;
import com.robertx22.database.unique_items.bases.BaseUniqueStaff;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class StaffThunder extends BaseUniqueStaff {

    public StaffThunder() {

    }

    @Override
    public int Tier() {
        return 5;
    }

    @Override
    public String GUID() {
        return "uniquestaffthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AttackThunderDamageFlat(), new CriticalDamageFlat(), new ThunderPenePercent(), new DodgeFlat(), new ManaOnHitFlat());
    }

    @Override
    public String locDescForLangFile() {
        return Styles.YELLOW + "Thunderstorm Staff";
    }

    @Override
    public String locNameForLangFile() {
        return "Controlled power can bring both energy and destruction.";
    }
}
