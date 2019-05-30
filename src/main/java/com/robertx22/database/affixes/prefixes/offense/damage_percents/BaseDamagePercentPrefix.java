package com.robertx22.database.affixes.prefixes.offense.damage_percents;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;

public abstract class BaseDamagePercentPrefix extends Prefix {

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnly());
    }
}
