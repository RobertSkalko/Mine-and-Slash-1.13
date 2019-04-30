package com.robertx22.database.stats.stat_types.traits.bases;

import com.robertx22.database.stats.Trait;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public abstract class ConditionalTrait extends Trait {

    public abstract String descPrefix();

    @Override
    public ITextComponent Description() {
        return new TextComponentString(this.descPrefix());
    }

}
