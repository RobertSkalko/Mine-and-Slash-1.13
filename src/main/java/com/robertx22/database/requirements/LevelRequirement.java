package com.robertx22.database.requirements;

import com.robertx22.config.ModConfig;
import net.minecraft.util.math.MathHelper;

public class LevelRequirement extends BaseRequirement {

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
    public boolean meetsRequierment(GearRequestedFor requested) {

        minLevel = MathHelper.clamp(minLevel, 0, ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL
                .get());  // make sure min lvl is not higher than the maximum posible level in case it was decreased by config?

        int level = requested.gearData.level;

        if (level < minLevel || level > maxLevel) {
            return false;
        }

        return true;

    }

}