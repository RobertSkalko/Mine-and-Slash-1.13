package com.robertx22.mmorpg.config.dimensions;

import java.util.HashMap;

import net.minecraft.world.World;

public class DimensionsContainer {

    public static DimensionsContainer INSTANCE = new DimensionsContainer();

    public DimensionsContainer() {

	if (dimensionsList.isEmpty()) {
	    dimensionsList.put("minecraft:overworld", (DimensionConfig.Overworld()));
	    dimensionsList.put("minecraft:the_end", (DimensionConfig.End()));
	    dimensionsList.put("minecraft:the_nether", (DimensionConfig.Nether()));
	}
    }

    String version = "1.0";

    DimensionConfig defaultconfig = DimensionConfig.DefaultExtra();

    public HashMap<String, DimensionConfig> dimensionsList = new HashMap();

    public boolean hasConfig(World world) {
	return hasConfig(world.getDimension().getType().getRegistryName().toString());
    }

    private boolean hasConfig(String id) {
	if (dimensionsList.containsKey(id)) {
	    return true;
	}

	return false;
    }

    public DimensionConfig getConfig(World world) {
	return getConfig(world.getDimension().getType().getRegistryName().toString());

    }

    private DimensionConfig getConfig(String id) {

	if (dimensionsList.containsKey(id)) {
	    return this.dimensionsList.get(id);
	}

	return defaultconfig; // default
    }

}
