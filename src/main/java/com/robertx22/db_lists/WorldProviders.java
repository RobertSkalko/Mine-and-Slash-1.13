package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.dimensions.IWP;
import com.robertx22.dimensions.world_providers.DesertHillsIWP;

import net.minecraft.world.dimension.DimensionType;

public class WorldProviders {

    public static final DimensionType type = DimensionType.OVERWORLD;

    public static HashMap<String, IWP> All = new HashMap<String, IWP>() {
	{
	    {
		put(new DesertHillsIWP(type).GUID(), new DesertHillsIWP(type));

	    }
	}

    };

}
