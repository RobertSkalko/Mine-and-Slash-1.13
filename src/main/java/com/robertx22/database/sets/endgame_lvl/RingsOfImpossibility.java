package com.robertx22.database.sets.endgame_lvl;

import com.robertx22.database.gearitemslots.Ring;
import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.HashMap;

public class RingsOfImpossibility extends Set {

    @Override
    public int Weight() {
        return IWeighted.MythicWeight;
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new CriticalDamageFlat().multi(4));

                }
            }
        };
    }

    @Override
    public String locNameForLangFile() {
        return "Rings of Impossibility";
    }

    @Override
    public String GUID() {
        return "set_rings_of_impossibility";
    }

    @Override
    public Requirements requirements() {
        return new Requirements(LevelRequirement.endgameLVLOnly(), new SlotRequirement(new Ring()));
    }
}
