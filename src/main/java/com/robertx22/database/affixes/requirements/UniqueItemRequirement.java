package com.robertx22.database.affixes.requirements;

public class UniqueItemRequirement extends BaseAffixRequirement {

    @Override
    public boolean meetsRequierment(AffixRequested requested) {

        if (requested.gearData.isUnique() == false) {
            return false;
        }

        return true;

    }

}
