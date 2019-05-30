package com.robertx22.database.affixes.prefixes.misc;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;

public abstract class BaseThirstPrefix extends Prefix {

    @Override
    public int Weight() {
        return this.RareWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.jewerlyOnly());
    }
}
