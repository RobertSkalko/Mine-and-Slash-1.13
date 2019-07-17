package com.robertx22.mine_and_slash.loot;

import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

public class StatGen {

    public static int GenPercent(ItemRarity rarity) {

        return RandomUtils.RandomRange(rarity.StatPercents().Min, rarity.StatPercents().Max);
    }
}
