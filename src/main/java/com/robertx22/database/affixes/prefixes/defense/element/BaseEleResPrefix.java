package com.robertx22.database.affixes.prefixes.defense.element;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.affixes.requirements.AffixRequirements;
import com.robertx22.database.affixes.requirements.SlotRequirement;

public abstract class BaseEleResPrefix extends Prefix {

    @Override
    public int Weight() {
        return this.EpicWeight;
    }

    @Override
    public AffixRequirements requirements() {
        return new AffixRequirements(SlotRequirement.armorsOnly());
    }

}
