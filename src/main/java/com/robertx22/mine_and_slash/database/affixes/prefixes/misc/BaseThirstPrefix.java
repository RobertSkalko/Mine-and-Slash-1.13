package com.robertx22.mine_and_slash.database.affixes.prefixes.misc;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;

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
