package com.robertx22.database.sets.uniques;

import com.robertx22.database.requirements.ExactUniquesRequierement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.generated.LootTypeBonusFlat;
import com.robertx22.database.unique_items.bracelets.BraceletSetDrop;
import com.robertx22.database.unique_items.necklaces.NecklaceSetDrop;
import com.robertx22.uncommon.enumclasses.LootType;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.Arrays;
import java.util.HashMap;

public class TreasureFinder extends Set {

    @Override
    public String locNameForLangFile() {
        return "Treasure Finder";
    }

    @Override
    public int Weight() {
        return IWeighted.UniqueSetSuperCommonWeight;
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new LootTypeBonusFlat(LootType.All));
                }
            }
        };
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new ExactUniquesRequierement(Arrays.asList(new NecklaceSetDrop(), new BraceletSetDrop())));
    }

    @Override
    public String GUID() {
        return "treasure_finder";
    }
}
