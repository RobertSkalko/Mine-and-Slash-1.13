package com.robertx22.database.stats.stat_types.traits;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.Trait;
import com.robertx22.database.stats.stat_mods.multi.elemental.damage.SpellFireDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.elemental.damage.SpellNatureDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.elemental.damage.SpellThunderDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.elemental.damage.SpellWaterDamageMulti;
import com.robertx22.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class Elemental extends Trait implements IAffectsOtherStats {

    public static String GUID = "Elemental";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public int percent() {
        return 33;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new SpellWaterDamageMulti(), new SpellNatureDamageMulti(), new SpellThunderDamageMulti(), new SpellFireDamageMulti());

    }

}
