package com.robertx22.database.affixes.prefixes.misc;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.affixes.requirements.AffixRequirements;
import com.robertx22.database.affixes.requirements.SlotRequirement;

public abstract class BaseThirstPrefix extends Prefix {

    @Override
    public int Weight() {
        return this.RareWeight;
    }

    @Override
    public AffixRequirements requirements() {
        return new AffixRequirements(SlotRequirement.jewerlyOnly());
    }
}
