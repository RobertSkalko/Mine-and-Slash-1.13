package com.robertx22.database.stats.stat_types.spell_buff_traits;

import com.robertx22.database.stats.stat_effects.spell_buffs.EnergyRegenBuffEffect;
import com.robertx22.database.stats.stat_types.spell_buff_traits.base.SpellBuffTrait;
import com.robertx22.uncommon.interfaces.IStatEffect;

import java.util.Arrays;
import java.util.List;

public class BuffEnergyRegenTrait extends SpellBuffTrait {

    public static String GUID = "Buff Energy Regen";

    @Override
    public String Guid() {
        return GUID;
    }

    @Override
    public String unlocString() {
        return "buff_energy_regen";
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new EnergyRegenBuffEffect());
    }

}
