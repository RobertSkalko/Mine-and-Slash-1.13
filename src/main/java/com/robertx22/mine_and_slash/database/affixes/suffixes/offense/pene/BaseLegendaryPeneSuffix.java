package com.robertx22.mine_and_slash.database.affixes.suffixes.offense.pene;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;

public abstract class BaseLegendaryPeneSuffix extends Suffix {

    @Override
    public int Weight() {
        return this.LegendaryWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.jewerlyOnly(), LevelRequirement.fromLVL20());
    }
}
