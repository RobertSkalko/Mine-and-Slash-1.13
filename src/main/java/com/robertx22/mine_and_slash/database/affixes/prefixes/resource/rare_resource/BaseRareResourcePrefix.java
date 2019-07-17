package com.robertx22.mine_and_slash.database.affixes.prefixes.resource.rare_resource;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;

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