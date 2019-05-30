package com.robertx22.database.requirements;

public class UniqueItemRequirement extends BaseRequirement {

    @Override
    public boolean meetsRequierment(GearRequestedFor requested) {

        if (requested.gearData.isUnique() == false) {
            return false;
        }

        return true;

    }

}
