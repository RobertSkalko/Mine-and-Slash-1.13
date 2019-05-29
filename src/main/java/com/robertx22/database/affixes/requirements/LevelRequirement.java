package com.robertx22.database.affixes.requirements;

public class LevelRequirement extends BaseAffixRequirement {

    int minLevel = 0;
    int maxLevel = Integer.MAX_VALUE;

    public LevelRequirement(int minLevel) {
        this.minLevel = minLevel;
    }

    public LevelRequirement(int minLevel, int maxLevel) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    @Override
    public boolean meetsRequierment(AffixRequested requested) {

        int level = requested.gearData.level;

        if (level < minLevel || level > maxLevel) {
            return false;
        }

        return true;

    }

}