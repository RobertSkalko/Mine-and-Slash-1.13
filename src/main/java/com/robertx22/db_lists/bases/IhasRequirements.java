package com.robertx22.db_lists.bases;

import com.robertx22.database.requirements.GearRequestedFor;
import com.robertx22.database.requirements.Requirements;

public interface IhasRequirements {

    public abstract Requirements requirements();

    public default boolean meetsRequirements(GearRequestedFor requested) {

        return requirements().satisfiesAllRequirements(requested);

    }

}
