package com.robertx22.mmorpg.config;

import net.minecraft.world.dimension.DimensionType;

public class DimensionConfigs {

    public DimensionConfigs() {

    }

    public static DimensionConfigs Overworld() {
	return new DimensionConfigs(DimensionType.OVERWORLD.toString(), 1, 100);
    }

    public static DimensionConfigs DefaultExtra() {
	return new DimensionConfigs("", 1, 100);
    }

    public static DimensionConfigs Nether() {
	return new DimensionConfigs(DimensionType.NETHER.toString(), 10, 100);
    }

    public static DimensionConfigs End() {
	return new DimensionConfigs(DimensionType.THE_END.toString(), 25, 100);
    }

    public DimensionConfigs(String id, int min, int max) {
	this.DIMENSION_id = id;
	this.MINIMUM_MOB_LEVEL = min;
	this.MAXIMUM_MOB_LEVEL = max;
    }

    public DimensionConfigs(String id, int distance, int area, int min, int max) {
	this.DIMENSION_id = id;
	this.MOB_LEVEL_PER_DISTANCE = distance;
	this.MOB_LEVEL_ONE_AREA = area;
	this.MINIMUM_MOB_LEVEL = min;
	this.MAXIMUM_MOB_LEVEL = max;

    }

    public String DIMENSION_id = "";

    public int MOB_LEVEL_PER_DISTANCE = 250;

    public int MOB_LEVEL_ONE_AREA = 50;

    public int MAXIMUM_MOB_LEVEL = 100;

    public int MINIMUM_MOB_LEVEL = 1;

    public int LEVEL_FOR_MOBS_TO_BE_LEGENDARY = 10;

    public int LEVEL_FOR_MOBS_TO_BE_MYTHICAL = 20;

    public boolean SINGLEPLAYER_MOB_SCALING = false;

}
