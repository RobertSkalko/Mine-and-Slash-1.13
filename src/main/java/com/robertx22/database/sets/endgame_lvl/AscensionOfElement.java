package com.robertx22.database.sets.endgame_lvl;

import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.sets.ElementalSet;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.generated.ElementalAffinityFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalPeneFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalSpellDamagePercent;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.HashMap;

public class AscensionOfElement extends ElementalSet {

    public AscensionOfElement(Elements element) {
        super(element);
    }

    @Override
    public int Weight() {
        return IWeighted.LegendaryWeight;
    }

    @Override
    public Set newGeneratedInstance(Elements element) {
        return new AscensionOfElement(element);
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new ElementalSpellDamagePercent(element));
                    put(3, new ElementalPeneFlat(element));
                    put(4, new ElementalAffinityFlat(element));

                }
            }
        };
    }

    @Override
    public String locNameForLangFile() {
        return "Ascension of " + this.element.name();
    }

    @Override
    public String GUID() {
        return "ascension_of_" + element.name();
    }

    @Override
    public Requirements requirements() {
        return new Requirements(LevelRequirement.endgameLVLOnly(), SlotRequirement.armorsOnlyNoOffHand());
    }
}
