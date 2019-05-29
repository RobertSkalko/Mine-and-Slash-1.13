package com.robertx22.database.affixes.prefixes.resource.rare_resource;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.affixes.requirements.AffixRequirements;
import com.robertx22.database.affixes.requirements.SlotRequirement;

public abstract class BaseRareResourcePrefix extends Prefix {

    @Override
    public AffixRequirements requirements() {
        return new AffixRequirements(SlotRequirement.jewerlyOnly());
    }

    @Override
    public int Weight() {
        return this.LegendaryWeight;
    }

}