package com.robertx22.database.sets.uniques;

import com.robertx22.database.requirements.ExactUniquesRequierement;
import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.WisdomFlat;
import com.robertx22.database.unique_items.helmet.HelmetWisdom;
import com.robertx22.database.unique_items.shields.ShieldWisdom;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.Arrays;
import java.util.HashMap;

public class WisdomOfTheElders extends Set {

    @Override
    public String locNameForLangFile() {
        return "Wisdom of the Elders";
    }

    @Override
    public int Weight() {
        return IWeighted.UniqueSetSuperCommonWeight;
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new WisdomFlat().multi(3));
                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(LevelRequirement.fromLVL50(), new ExactUniquesRequierement(Arrays
                .asList(new HelmetWisdom(), new ShieldWisdom())));
    }

    @Override
    public String GUID() {
        return "wisdom_of_the_elders";
    }
}