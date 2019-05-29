package com.robertx22.database.affixes.requirements;

public class UniqueTierRequirement extends UniqueItemRequirement {

    int minTier = 0;
    int maxTier = Integer.MAX_VALUE;

    public UniqueTierRequirement(int minTier) {
        this.minTier = minTier;
    }

    @Override
    public boolean meetsRequierment(AffixRequested requested) {

        Boolean superreq = super.meetsRequierment(requested);

        if (superreq == false) {
            return false;
        }

        int tier = requested.gearData.uniqueStats.getUniqueItem().Tier();

        if (tier < minTier || tier > maxTier) {
            return false;
        }

        return true;

    }

}
