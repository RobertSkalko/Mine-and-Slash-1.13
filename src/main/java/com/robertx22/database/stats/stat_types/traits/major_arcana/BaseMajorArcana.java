package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.Trait;
import com.robertx22.uncommon.CLOC;
import net.minecraft.util.text.ITextComponent;

public abstract class BaseMajorArcana extends Trait implements INameSuffix {

    @Override
    public ITextComponent locSuffix() {
        return CLOC.word("major_arcana");
    }
}
