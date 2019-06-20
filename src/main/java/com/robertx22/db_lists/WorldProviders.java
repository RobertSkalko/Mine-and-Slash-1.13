package com.robertx22.db_lists;

import com.robertx22.database.world_providers.*;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;

public class WorldProviders {

    public static WorldProviders INSTANCE = new WorldProviders();

    public static HashMap<String, BaseWorldProvider> All = new HashMap<String, BaseWorldProvider>() {
        {
            {
                put(new DesertHillsIWP(null, null).GUID(), new DesertHillsIWP(null, null));
                put(new SnowyMountainsIWP(null, null).GUID(), new SnowyMountainsIWP(null, null));
                put(new SavannaIWP(null, null).GUID(), new SavannaIWP(null, null));
                put(new RockDesertIWP(null, null).GUID(), new RockDesertIWP(null, null));
                put(new RockMountainsIWP(null, null).GUID(), new RockMountainsIWP(null, null));
                put(new BirchForestIWP(null, null).GUID(), new BirchForestIWP(null, null));
                put(new NetherIWP(null, null).GUID(), new NetherIWP(null, null));

            }

        }

    };

    public static IWP byBiome(Biome biome) {
        IWP first = null;

        try {
            first = WorldProviders.All.values()
                    .stream()
                    .filter(x -> x.getBiome().equals(biome))
                    .findFirst()
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (first != null) {
            return first;
        } else {
            return new BirchForestIWP(null, null);
        }
    }

    public BaseWorldProvider random(IWPRandomConfig config) {
        return (RandomUtils.weightedRandom(WorldProviders.All.values()));
    }

    public BaseWorldProvider random() {
        return random(new IWPRandomConfig());
    }

    public static class IWPRandomConfig {
    }

}
