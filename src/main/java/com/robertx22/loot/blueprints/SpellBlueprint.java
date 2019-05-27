package com.robertx22.loot.blueprints;

import com.robertx22.database.rarities.RaritiesContainer;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.Spells;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class SpellBlueprint extends ItemBlueprint {

    public SpellBlueprint(int level) {
        super(level);
    }

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {

        return Rarities.Spells;

    }

    public BaseSpell GetSpell() {

        if (randomGUID) {

            return RandomUtils.weightedRandom(Spells.All.values());

        } else {

            return Spells.All.get(GUID);
        }

    }

}
