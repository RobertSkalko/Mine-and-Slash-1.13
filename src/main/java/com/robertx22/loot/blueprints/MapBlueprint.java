package com.robertx22.loot.blueprints;

import com.robertx22.database.rarities.RaritiesContainer;
import com.robertx22.db_lists.Rarities;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.math.MathHelper;

public class MapBlueprint extends ItemBlueprint {

    public static final int PERMADEATH_CHANCE = 10;
    private static final int MAX_MAP_TIER = 20;

    public MapBlueprint(int level, int worldTier) {
        super(level);
        this.setTier(worldTier);

    }

    private int tier = 0;
    private boolean tierRange = true;
    private int tierVariance = 2;

    public void setTier(int i) {
        tier = i;
    }

    public int getTier() {

        if (tierRange) {
            int thetier = RandomUtils.RandomRange(tier - tierVariance, tier + tierVariance);

            return MathHelper.clamp(thetier, 1, MAX_MAP_TIER);

        } else {
            return tier;
        }

    }

    public boolean getIsPermaDeath() {

        return RandomUtils.roll(PERMADEATH_CHANCE);

    }

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {

        return Rarities.Maps;

    }

}
