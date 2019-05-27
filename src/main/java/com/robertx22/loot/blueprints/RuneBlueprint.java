package com.robertx22.loot.blueprints;

import com.robertx22.database.rarities.RaritiesContainer;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.Runes;
import com.robertx22.items.runes.base.BaseRuneItem;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class RuneBlueprint extends ItemBlueprint {

    public RuneBlueprint(int level) {
        super(level);

    }

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {
        return Rarities.Runes;
    }

    BaseRuneItem rune = null;

    public BaseRuneItem getRuneItem() {

        if (rune != null) {
            return rune;
        } else {

            rune = RandomUtils.weightedRandom(Runes.All.values());

            return rune;

        }

    }

}
