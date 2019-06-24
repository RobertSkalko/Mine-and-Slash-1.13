package com.robertx22.db_lists;

import com.robertx22.world_gen.types.FeatureType;

public class Templates {

    static final String treasure = "treasure/";
    static final String decoration = "decoration/";

    public static final FeatureType bigWoodPillar = new FeatureType(decoration + "big_wood_pillar0")
            .lowerBy(2);
    public static final FeatureType smallWoodPillar = new FeatureType(decoration + "small_wood_pillar0")
            .lowerBy(2);
    public static final FeatureType lampPillar = new FeatureType(decoration + "lamp").lowerBy(2);
    public static final FeatureType smallTreasure0 = new FeatureType(treasure + "small0").lowerBy(2);
    public static final FeatureType smallTreasure1 = new FeatureType(treasure + "small1");
    public static final FeatureType smallTreasure2 = new FeatureType(treasure + "trapped0")
            .lowerBy(2);

}
