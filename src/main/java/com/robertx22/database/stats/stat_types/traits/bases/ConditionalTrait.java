package com.robertx22.database.stats.stat_types.traits.bases;

import com.robertx22.database.stats.Trait;

public abstract class ConditionalTrait extends Trait {

    public abstract String descPrefix();

    @Override
    public String Description() {
	return this.descPrefix();
    }

}
