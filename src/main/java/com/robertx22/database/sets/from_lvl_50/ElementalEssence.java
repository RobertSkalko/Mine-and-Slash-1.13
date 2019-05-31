package com.robertx22.database.sets.from_lvl_50;

import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.AllEleSpellDmgFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.AllEleResistFlat;

import java.util.HashMap;

public class ElementalEssence extends Set {

    @Override
    public String locNameForLangFile() {
        return "Elemental Essence";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new AllEleResistFlat());
                    put(3, new AllEleSpellDmgFlat());

                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.jewerlyOnly(), LevelRequirement.fromLVL50());
    }

    @Override
    public String GUID() {
        return "elemental_essence";
    }
}
