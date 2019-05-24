package com.robertx22.db_lists;

import com.robertx22.dimensions.BaseWorldProvider;
import com.robertx22.dimensions.IWP;
import com.robertx22.dimensions.world_providers.DesertHillsIWP;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import java.util.HashMap;

public class WorldProviders implements IRandom<IWP, WorldProviders.IWPRandomConfig> {

    public static WorldProviders INSTANCE = new WorldProviders();

    // private static DimensionType type = DimensionType.THE_END;

    public static HashMap<String, BaseWorldProvider> All = new HashMap<String, BaseWorldProvider>() {
        {
            {
                put(new DesertHillsIWP().GUID(), new DesertHillsIWP());

            }

        }

    };

    @Override
    public IWP random(IWPRandomConfig config) {
        return ((IWP) RandomUtils.WeightedRandom(ListUtils.CollectionToList(WorldProviders.All
                .values())));
    }

    @Override
    public IWP random() {
        return random(new IWPRandomConfig());
    }

    public static class IWPRandomConfig {
    }

}
