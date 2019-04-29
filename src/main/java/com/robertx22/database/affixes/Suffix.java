package com.robertx22.database.affixes;

import com.robertx22.uncommon.CLOC;

public abstract class Suffix extends BaseAffix {

    public String locName() {
        return CLOC.suffix(GUID().toLowerCase().replaceAll(" ", "_"));
    }

}
