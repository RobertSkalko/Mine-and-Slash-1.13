package com.robertx22.database.stats.stat_types.core_stats;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stats.stat_mods.multi.elemental.all_damage.AllFireDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.elemental.all_damage.AllNatureDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.elemental.all_damage.AllThunderDamageMulti;
import com.robertx22.database.stats.stat_mods.multi.elemental.all_damage.AllWaterDamageMulti;

import java.util.Arrays;
import java.util.List;

public class Intelligence extends BaseCoreStat {

    @Override
    public String locDescForLangFile() {
        return "Increase Mana Regen and Mana, All Elemental damage";
    }

    @Override
    public String GUID() {
        return "Intelligence";
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(new ManaRegenFlat(), new ManaFlat(), new AllFireDamageMulti(), new AllWaterDamageMulti(), new AllNatureDamageMulti(), new AllThunderDamageMulti());
    }

    @Override
    public String locNameForLangFile() {
        return "Intelligence";
    }
}




