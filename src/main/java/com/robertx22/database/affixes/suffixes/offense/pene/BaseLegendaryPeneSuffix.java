package com.robertx22.database.affixes.suffixes.offense.pene;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;

public abstract class BaseLegendaryPeneSuffix extends Suffix {

    @Override
    public int Weight() {
        return this.LegendaryWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.jewerlyOnly(), new LevelRequirement(25));
    }
}
