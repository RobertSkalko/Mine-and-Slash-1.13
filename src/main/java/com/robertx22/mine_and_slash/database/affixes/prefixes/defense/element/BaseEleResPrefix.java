package com.robertx22.mine_and_slash.database.affixes.prefixes.defense.element;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;

public abstract class BaseEleResPrefix extends Prefix {

    @Override
    public int Weight() {
        return this.EpicWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnly());
    }

}
