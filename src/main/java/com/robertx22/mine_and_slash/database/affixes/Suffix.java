package com.robertx22.mine_and_slash.database.affixes;

import com.robertx22.mine_and_slash.mmorpg.Ref;

public abstract class Suffix extends BaseAffix {

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".suffix." + formattedGUID();
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Suffixes;
    }
}
