package com.robertx22.database.affixes.prefixes.resource.rare_resource;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;

public abstract class BaseRareResourcePrefix extends Prefix {

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.jewerlyOnly(), LevelRequirement.fromLVL10());
    }

    @Override
    public int Weight() {
        return this.LegendaryWeight;
    }

}