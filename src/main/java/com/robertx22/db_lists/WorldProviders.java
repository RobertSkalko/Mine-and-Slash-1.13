package com.robertx22.db_lists;

import com.robertx22.database.world_providers.*;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import java.util.HashMap;

public class WorldProviders {

    public static WorldProviders INSTANCE = new WorldProviders();

    public static HashMap<String, BaseWorldProvider> All = new HashMap<String, BaseWorldProvider>() {
        {
            {
                put(new DesertHillsIWP().GUID(), new DesertHillsIWP());
                put(new SnowyMountainsIWP().GUID(), new SnowyMountainsIWP());
                put(new SavannaIWP().GUID(), new SavannaIWP());
                put(new RockDesertIWP().GUID(), new RockDesertIWP());
                put(new RockMountainsIWP().GUID(), new RockMountainsIWP());
                put(new BirchForestIWP().GUID(), new BirchForestIWP());
                put(new NetherIWP().GUID(), new NetherIWP());

            }

        }

    };

    public BaseWorldProvider random(IWPRandomConfig config) {
        return (RandomUtils.weightedRandom(WorldProviders.All.values()));
    }

    public BaseWorldProvider random() {
        return random(new IWPRandomConfig());
    }

    public static class IWPRandomConfig {
    }

}
