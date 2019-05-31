package com.robertx22.database.sets.low_lvl.elemental_jewerly;

import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.HealthPercent;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellNatureDamagePercent;

import java.util.HashMap;

public class ForestGuardian extends Set {

    @Override
    public String locNameForLangFile() {
        return "Forest Guardian";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new HealthPercent());
                    put(3, new SpellNatureDamagePercent());

                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.jewerlyOnly(), LevelRequirement.lowLVLOnly());
    }

    @Override
    public String GUID() {
        return "forest_guardian";
    }
}
