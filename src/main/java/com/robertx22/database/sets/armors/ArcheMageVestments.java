package com.robertx22.database.sets.armors;

import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.ManaRegenPercent;

import java.util.HashMap;

public class ArcheMageVestments extends Set {

    @Override
    public String locNameForLangFile() {
        return "Archmage's Vestments";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {

                    put(2, new ManaRegenPercent());

                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnlyNoOffHand());
    }

    @Override
    public String GUID() {
        return "archemage_vestments";
    }
}