package com.robertx22.database.stats.stat_mods.traits;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_mods.BaseTraitMod;
import com.robertx22.database.stats.stat_types.traits.Elemental;

public class ElementalFlat extends BaseTraitMod {

	public ElementalFlat() {
	}

	@Override
	public String GUID() {
		return "ElementalFlat";
	}

	@Override
	public Stat GetBaseStat() {
		return new Elemental();
	}

}
