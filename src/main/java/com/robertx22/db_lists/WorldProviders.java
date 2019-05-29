package com.robertx22.db_lists;

import com.robertx22.database.world_providers.*;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import java.util.HashMap;
import java.util.List;

public class WorldProviders implements IRandom<IWP, WorldProviders.IWPRandomConfig> {

    public static WorldProviders INSTANCE = new WorldProviders();

    public static HashMap<String, BaseWorldProvider> All = new HashMap<String, BaseWorldProvider>() {
        {
            {
                put(new DesertHillsIWP().GUID(), new DesertHillsIWP());
                put(new SnowyMountainsIWP().GUID(), new SnowyMountainsIWP());
                put(new SavannaIWP().GUID(), new SavannaIWP());
                put(new RockDesertIWP().GUID(), new RockDesertIWP());
                put(new RockMountainsIWP().GUID(), new RockMountainsIWP());
                put(new DefaultIWP().GUID(), new DefaultIWP());
                put(new NetherIWP().GUID(), new NetherIWP());

            }

        }

    };

    @Override
    public IWP random(IWPRandomConfig config) {
        return (RandomUtils.weightedRandom(WorldProviders.All.values()));
    }

    @Override
    public IWP random() {
        return random(new IWPRandomConfig());
    }

    @Override
    public List<IWP> allThatMeetRequirement(IWPRandomConfig iwpRandomConfig) {
        return null;
    }

    public static class IWPRandomConfig {
    }

}
