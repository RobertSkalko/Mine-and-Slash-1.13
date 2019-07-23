package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.rarities.items.UniqueItem;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
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

    public IUnique getUnique() {
        if (this.uniqueIsRandom) {

            if (this.randomTier == false) {
                return RandomUtils.weightedRandom(SlashRegistry.UniqueGears()
                        .getFiltered(x -> x.Tier() == tier));
            } else {
                return randomUnique();
            }
        } else {
            return SlashRegistry.UniqueGears().get(this.guid);
        }

    }

    private IUnique randomUnique() {

        List<IUnique> possible = SlashRegistry.UniqueGears()
                .getFiltered(SlashRegistry.PREDICATES.ofTierOrLess(tier)
                        .and(x -> ((IUnique) x).slot()
                                .equals(this.gearType) || gearType.equals("random") || gearType
                                .equals("")));

        IUnique unique = RandomUtils.weightedRandom(possible);

        return unique;

    }

}
