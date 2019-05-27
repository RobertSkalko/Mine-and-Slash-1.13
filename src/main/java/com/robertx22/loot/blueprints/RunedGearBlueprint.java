package com.robertx22.loot.blueprints;

import com.robertx22.database.rarities.RaritiesContainer;
import com.robertx22.db_lists.Rarities;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;

public class RunedGearBlueprint extends GearBlueprint {

    public RunedGearBlueprint(int level) {
        super(level);

    }

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {
        return Rarities.RunedItems;
    }
}
