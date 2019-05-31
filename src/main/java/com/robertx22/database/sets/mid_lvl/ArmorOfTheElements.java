package com.robertx22.database.sets.mid_lvl;

import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.AllEleDmgFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.AllEleSpellDmgFlat;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellFireDamagePercent;

import java.util.HashMap;

public class ArmorOfTheElements extends Set {

    @Override
    public String locNameForLangFile() {
        return "Armor of the Elements";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new SpellFireDamagePercent());
                    put(3, new AllEleSpellDmgFlat());
                    put(4, new AllEleDmgFlat());

                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnlyNoOffHand(), LevelRequirement.midLVLOnly());
    }

    @Override
    public String GUID() {
        return "artifact_armor";
    }
}
