package com.robertx22.database.affixes.prefixes.offense.imbued;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.SlotRequirement;

public abstract class BaseImbuedPrefix extends Prefix {

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.jewerlyOnly(), new LevelRequirement(15));
    }

}

