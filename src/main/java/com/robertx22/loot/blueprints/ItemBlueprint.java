package com.robertx22.loot.blueprints;

import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.RaritiesContainer;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.math.MathHelper;

public abstract class ItemBlueprint {

    public ItemBlueprint(int level) {

        this.level = level;

    }

    public int MagicFind = 0;

    public int rarity;
    public boolean RandomRarity = true;
    public int level;
    public boolean LevelRange = true;
    public int LevelVariance = 3;

    public int minRarity = -1;
    public int maxRarity = 5;

    public int minLevel = 1;

    public abstract RaritiesContainer<? extends Rarity> getRarityContainer();

    public void SetSpecificRarity(int i) {

        rarity = i;
        RandomRarity = false;

    }

    public int GetRarity() {

        RaritiesContainer<? extends Rarity> rarities = getRarityContainer();

        if (RandomRarity) {

            if (minRarity > -1 || maxRarity < 5) {
                Rarity rar = rarities.random();

                while (rar.Rank() < minRarity || rar.Rank() > maxRarity) {
                    rar = rarities.random();
                }
                return rar.Rank();

            } else {
                return rarities.random().Rank();
            }
        } else {
            return rarity;
        }

    }

    public int GetLevel() {

        int lvl = level;

        if (LevelRange) {

            lvl = RandomUtils.RandomRange(level - LevelVariance, level + LevelVariance);

            level = lvl;

        }
        return MathHelper.clamp(lvl, this.minLevel, ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL
                .get());

    }
}
