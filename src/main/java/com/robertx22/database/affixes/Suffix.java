package com.robertx22.database.affixes;

import com.robertx22.uncommon.CLOC;
import net.minecraft.util.text.ITextComponent;

public abstract class Suffix extends BaseAffix {

    @Override
    public ITextComponent locName() {
        return CLOC.suffix(GUID().toLowerCase().replaceAll(" ", "_"));
    }

}
