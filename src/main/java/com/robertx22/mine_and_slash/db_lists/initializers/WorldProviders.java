package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.world_providers.*;
import com.robertx22.mine_and_slash.db_lists.newregistry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.newregistry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.newregistry.SlashRegistryType;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;

public class WorldProviders implements ISlashRegistryInit {

    public static WorldProviders INSTANCE = new WorldProviders();

    private static HashMap<String, BaseWorldProvider> All = new HashMap<String, BaseWorldProvider>() {
        {
            {
                put(new DesertHillsIWP(null, null).GUID(), new DesertHillsIWP(null, null));
                put(new SnowyMountainsIWP(null, null).GUID(), new SnowyMountainsIWP(null, null));
                put(new SavannaIWP(null, null).GUID(), new SavannaIWP(null, null));
                put(new RockDesertIWP(null, null).GUID(), new RockDesertIWP(null, null));
                put(new RockMountainsIWP(null, null).GUID(), new RockMountainsIWP(null, null));
                put(new BirchForestIWP(null, null).GUID(), new BirchForestIWP(null, null));
                put(new NetherIWP(null, null).GUID(), new NetherIWP(null, null));
                put(new SwampHillsIWP(null, null).GUID(), new SwampHillsIWP(null, null));

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

    @Override
    public void registerAll() {
        All.values()
                .forEach(x -> SlashRegistry.getRegistry(SlashRegistryType.WORLD_PROVIDER)
                        .register(x));

    }

}