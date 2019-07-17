package com.robertx22.mine_and_slash.database.affixes;

import com.robertx22.mine_and_slash.mmorpg.Ref;

public abstract class Prefix extends BaseAffix {

    public Prefix() {

    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".prefix." + formattedGUID();
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Prefixes;
    }
}
