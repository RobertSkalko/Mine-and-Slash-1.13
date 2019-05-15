package com.robertx22.loot.blueprints;

import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.loot.create.RarityGen;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.math.MathHelper;

public class ItemBlueprint {

    public ItemBlueprint(int level) {

        if (level > ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get()) {
            level = ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get();
        }
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

    public void SetSpecificRarity(int i) {

        rarity = i;
        RandomRarity = false;

    }

    public int GetRarity() {

        if (RandomRarity) {

            if (minRarity > -1 || maxRarity < 5) {
                ItemRarity rar = Rarities.Items.get(RarityGen.Random(0, ListUtils.CollectionToList(Rarities.Items))
                        .Rank());

                while (rar.Rank() < minRarity || rar.Rank() > maxRarity) {
                    rar = Rarities.Items.get(RarityGen.Random(0, ListUtils.CollectionToList(Rarities.Items))
                            .Rank());
                }
                return rar.Rank();

            } else {
                return RarityGen.Random(0, ListUtils.CollectionToList(Rarities.Items))
                        .Rank();
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
