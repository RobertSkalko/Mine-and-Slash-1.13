package com.robertx22.database.affixes.prefixes.offense.imbued;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.affixes.requirements.AffixRequirements;
import com.robertx22.database.affixes.requirements.SlotRequirement;

public abstract class BaseImbuedPrefix extends Prefix {

    @Override
    public AffixRequirements requirements() {
        return new AffixRequirements(SlotRequirement.jewerlyOnly());
    }
}

