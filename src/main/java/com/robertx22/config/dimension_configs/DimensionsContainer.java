package com.robertx22.config.dimension_configs;

import net.minecraft.world.World;

import java.util.HashMap;

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
        String id = getId(world);

        return hasConfig(id);
    }

    private boolean hasConfig(String id) {
        if (dimensionsList.containsKey(id)) {
            return true;
        }

        return false;
    }

    private String getId(World world) {

        String id = "";

        if (world != null && world.getDimension() != null) {
            if (world.getDimension().getType().getRegistryName() != null)
                id = world.getDimension().getType().getRegistryName().toString();
        }
        return id;
    }

    public DimensionConfig getConfig(World world) {
        String id = getId(world);

        if (dimensionsList.containsKey(id)) {

            return getConfig(id);
        }

        return defaultconfig; // default

    }

    private DimensionConfig getConfig(String id) {

        if (dimensionsList.containsKey(id)) {
            return this.dimensionsList.get(id);
        }

        return defaultconfig; // default
    }

}
