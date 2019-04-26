package com.robertx22.database.stats.stat_types.traits.low_dodge;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.multi.defense.ArmorMulti;
import com.robertx22.database.stats.stat_types.traits.bases.BaseTraitLowDodge;

public class LowDodgeAddArmor extends BaseTraitLowDodge {

    @Override
    public List<StatMod> getStats() {
	return Arrays.asList(new ArmorMulti());

    }

    @Override
    public String Guid() {
	return "LowDodgeAddArmor";
    }

    @Override
    public String unlocString() {
	return "armor_on_low_dodge";
    }

}
