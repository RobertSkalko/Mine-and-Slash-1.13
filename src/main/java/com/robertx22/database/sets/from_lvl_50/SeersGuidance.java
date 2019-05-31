package com.robertx22.database.sets.from_lvl_50;

import com.robertx22.database.gearitemslots.Charm;
import com.robertx22.database.gearitemslots.Helmet;
import com.robertx22.database.gearitemslots.Necklace;
import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.WisdomFlat;
import com.robertx22.database.stats.stat_mods.flat.misc.BonusExpFlat;

import java.util.Arrays;
import java.util.HashMap;

public class SeersGuidance extends Set {

    @Override
    public String locNameForLangFile() {
        return "Seer's Guidance";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new BonusExpFlat());
                    put(3, new WisdomFlat());

                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new SlotRequirement(Arrays.asList(new Helmet(), new Necklace(), new Charm())), LevelRequirement
                .fromLVL50());
    }

    @Override
    public String GUID() {
        return "seers_guidance";
    }
}
