package com.robertx22.database.affixes;

import com.robertx22.mmorpg.Ref;

public abstract class Suffix extends BaseAffix {

    @Override
    public String locNameLangFileGUID(String guid) {
        return Ref.MODID + ".suffix." + guid;
    }
}
