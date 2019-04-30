package com.robertx22.database.stats.stat_types.traits.atronachs;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.Trait;
import com.robertx22.database.stats.stat_mods.multi.elemental.damage.SpellNatureDamageMulti;
import com.robertx22.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class ThunderAtronach extends Trait implements IAffectsOtherStats {

    public static String GUID = "Thunder Atronach";

    @Override
    public String unlocString() {
        return "thunder_atronach";
    }

    @Override
    public String Guid() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new SpellNatureDamageMulti());

    }

}
