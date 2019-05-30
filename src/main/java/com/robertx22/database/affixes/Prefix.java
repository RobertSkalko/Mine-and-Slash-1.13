package com.robertx22.database.affixes;

import com.robertx22.mmorpg.Ref;

public abstract class Prefix extends BaseAffix {

    public Prefix() {

    }

    @Override
    public String locNameLangFileGUID(String guid) {
        return Ref.MODID + ".prefix." + guid;
    }
}
