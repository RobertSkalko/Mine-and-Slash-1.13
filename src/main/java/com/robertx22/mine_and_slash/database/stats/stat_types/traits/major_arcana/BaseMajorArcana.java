package com.robertx22.mine_and_slash.database.stats.stat_types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import net.minecraft.util.text.ITextComponent;

public abstract class BaseMajorArcana extends Trait implements INameSuffix {

    @Override
    public ITextComponent locSuffix() {
        return Words.Major_Arcana.locName();
    }

    @Override
    public int Weight() {
        return IWeighted.MythicWeight;
    }
}