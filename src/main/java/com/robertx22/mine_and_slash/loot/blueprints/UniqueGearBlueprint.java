package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.rarities.items.UniqueItem;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.db_lists.initializers.UniqueItems;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

import java.util.List;

public class UniqueGearBlueprint extends GearBlueprint {

    public UniqueGearBlueprint(int level, int map_tier, boolean randomTier) {
        super(level);
        this.randomTier = randomTier;
        this.map_tier = map_tier;

    }

    public UniqueGearBlueprint(int level, String guid) {
        super(level);
        this.guid = guid;
        this.uniqueIsRandom = false;

    }

    @Override
    public int getRarity() {
        return new UniqueItem().Rank();
    }

    private String guid = "";
    public boolean uniqueIsRandom = true;

    public int map_tier = 0;
    public int tier = -1;

    private boolean randomTier = true;

    public void setSpecificID(String id) {

        this.guid = id;
        this.uniqueIsRandom = false;

    }

    public int GetTier() {

        if (tier < 0) {

            if (randomTier) {

                if (map_tier == 0) {
                    tier = 0;
                } else {
                    tier = RandomUtils.RandomRange(0, this.map_tier);
                }

            } else {

                tier = map_tier;
            }
        }

        return tier;

    }

    public IUnique getUnique() {

        if (this.uniqueIsRandom) {
            tier = this.GetTier();

            if (this.randomTier == false) {
                return RandomUtils.weightedRandom(UniqueItems.getAllUniquesOfTier(map_tier, UniqueItems.ITEMS
                        .values()));
            } else {
                return randomUnique();
            }
        } else {
            return (IUnique) UniqueItems.ITEMS.get(this.guid);
        }

    }

    private IUnique randomUnique() {

        List<IUnique> possible = UniqueItems.filterUniquesByType(gearType, UniqueItems.getAllPossibleUniqueDrops(map_tier, UniqueItems.ITEMS
                .values()));

        IUnique unique = RandomUtils.weightedRandom(possible);

        return unique;

    }

}
