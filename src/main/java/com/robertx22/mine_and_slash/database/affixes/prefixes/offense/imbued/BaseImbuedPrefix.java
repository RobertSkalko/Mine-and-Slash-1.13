package com.robertx22.mine_and_slash.database.affixes.prefixes.offense.imbued;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;

public abstract class BaseImbuedPrefix extends Prefix {

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.jewerlyOnly(), LevelRequirement.fromLVL10());
    }

}

