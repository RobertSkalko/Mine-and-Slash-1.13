package com.robertx22.database.affixes.prefixes.resource.rare_resource;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;

public abstract class BaseRareResourcePrefix extends Prefix {

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.jewerlyOnly());
    }

    @Override
    public int Weight() {
        return this.LegendaryWeight;
    }

}