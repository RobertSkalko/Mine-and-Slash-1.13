package com.robertx22.database.unique_items.rings;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.BaseConversionFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellWaterDamageFlat;
import com.robertx22.database.unique_items.bases.BaseUniqueRing;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class RingWaterFire extends BaseUniqueRing {

    public RingWaterFire() {

    }

    @Override
    public int Tier() {
        return 15;
    }

    @Override
    public String GUID() {
        return "ringwaterfire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new SpellFireDamageFlat(), new SpellWaterDamageFlat(), new BaseConversionFlat(Elements.Fire, Elements.Water), new BaseConversionFlat(Elements.Water, Elements.Fire));
    }

}
