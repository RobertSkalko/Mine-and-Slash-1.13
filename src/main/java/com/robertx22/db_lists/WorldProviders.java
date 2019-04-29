package com.robertx22.db_lists;

import com.robertx22.dimensions.BaseWorldProvider;
import com.robertx22.dimensions.world_providers.DesertHillsIWP;
import net.minecraft.world.dimension.DimensionType;

import java.util.HashMap;

public class WorldProviders {

    public static final DimensionType type = DimensionType.OVERWORLD;

    public static HashMap<String, BaseWorldProvider> All = new HashMap<String, BaseWorldProvider>() {
        {
            {
                put(new DesertHillsIWP(type).GUID(), new DesertHillsIWP(type));

            }

        }

    };

}
