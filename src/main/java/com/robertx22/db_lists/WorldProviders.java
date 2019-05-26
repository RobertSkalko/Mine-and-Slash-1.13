package com.robertx22.db_lists;

import com.robertx22.database.world_providers.*;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import java.util.HashMap;

public class WorldProviders implements IRandom<IWP, WorldProviders.IWPRandomConfig> {

    public static WorldProviders INSTANCE = new WorldProviders();

    public static HashMap<String, BaseWorldProvider> All = new HashMap<String, BaseWorldProvider>() {
        {
            {
                put(new DesertHillsIWP().GUID(), new DesertHillsIWP());
                put(new SnowyMountainsIWP().GUID(), new SnowyMountainsIWP());
                put(new JungleIWP().GUID(), new JungleIWP());
                put(new RockDesertIWP().GUID(), new RockDesertIWP());
                put(new RockMountainsIWP().GUID(), new RockMountainsIWP());
                put(new SurfaceIWP().GUID(), new SurfaceIWP());
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
