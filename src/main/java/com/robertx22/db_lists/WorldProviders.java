package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.dimensions.IWP;
import com.robertx22.dimensions.world_providers.CliffWP;

public class WorldProviders {
    public static HashMap<String, IWP> All = new HashMap<String, IWP>() {
	{
	    {
		put(new CliffWP().GUID(), new CliffWP());

	    }
	}
    };

}
