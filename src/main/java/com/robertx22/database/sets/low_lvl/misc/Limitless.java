package com.robertx22.database.sets.low_lvl.misc;

import com.robertx22.database.gearitemslots.Charm;
import com.robertx22.database.gearitemslots.Ring;
import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.EnergyRegenPercent;

import java.util.Arrays;
import java.util.HashMap;

public class Limitless extends Set {

    @Override
    public String locNameForLangFile() {
        return "Limitless";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new EnergyRegenPercent());

                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new SlotRequirement(Arrays.asList(new Ring(), new Charm())), LevelRequirement
                .lowLVLOnly());
    }

    @Override
    public String GUID() {
        return "limitless";
    }

}