package com.robertx22.database.affixes;

import com.robertx22.uncommon.CLOC;
import net.minecraft.util.text.ITextComponent;

public abstract class Prefix extends BaseAffix {

    public Prefix() {

    }

    @Override
    public ITextComponent locName() {

        return CLOC.prefix(GUID().toLowerCase().replaceAll(" ", "_"));
    }
}
