package com.robertx22.database.stats.stat_types.traits.atronachs;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.Trait;
import com.robertx22.database.stats.stat_mods.multi.elemental.damage.SpellFireDamageMulti;
import com.robertx22.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class FireAtronach extends Trait implements IAffectsOtherStats {

    public static String GUID = "Fire Atronach";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new SpellFireDamageMulti());

    }

    @Override
    public String locNameForLangFile() {
        return "Fire Atronach";
    }
}
