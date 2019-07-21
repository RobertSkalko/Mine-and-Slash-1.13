package com.robertx22.mine_and_slash.uncommon.interfaces.data_items;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

public interface IRarity<R extends Rarity> {

    public int getRarityRank();

    public R getRarity();

    public default boolean isUnique() {
        return this.getRarityRank() == -1;
    }
}
