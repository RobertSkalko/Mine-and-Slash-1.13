package com.robertx22.database.sets.from_lvl_50;

import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.NatureSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.ThunderSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.HashMap;

public class MysticalOrnaments extends Set {

    @Override
    public String locNameForLangFile() {
        return "Mystical Ornaments";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new NatureResistFlat());
                    put(3, new ThunderResistFlat());
                    put(4, new NatureSpellToAttackFlat());
                    put(5, new ThunderSpellToAttackFlat());
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
        return new Requirements(SlotRequirement.jewerlyOnly(), LevelRequirement.fromLVL50());
    }

    @Override
    public String GUID() {
        return "mystical_ornaments";
    }
}
