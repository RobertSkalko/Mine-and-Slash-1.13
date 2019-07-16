package com.robertx22.database.sets.from_lvl_50;

import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.multi.defense.HealthMulti;
import com.robertx22.database.stats.stat_mods.percent.HealthPercent;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.HashMap;

public class TheAscended extends Set {

    @Override
    public String locNameForLangFile() {
        return "The Ascended";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new HealthFlat());
                    put(3, new HealthPercent().multi(2));
                    put(4, new HealthMulti().multi(2));
                }
            }
        };
    }

    @Override
    public int Weight() {
        return IWeighted.EpicWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnlyNoOffHand(), LevelRequirement.fromLVL50());
    }

    @Override
    public String GUID() {
        return "the_ascended";
    }
}
