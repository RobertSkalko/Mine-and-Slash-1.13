package com.robertx22.database.stats.stat_types.traits.high_dodge;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.database.stats.stat_types.traits.bases.BaseTraitHighCritHit;

import java.util.Arrays;
import java.util.List;

public class HighDodgeAddPhysDamage extends BaseTraitHighCritHit {

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new PhysicalDamagePercent());

    }

    @Override
    public String GUID() {
        return "HighDodgeAddPhysDamage";
    }

    @Override
    public String locNameForLangFile() {
        return "Phys Damage on High Dodge";
    }
}
