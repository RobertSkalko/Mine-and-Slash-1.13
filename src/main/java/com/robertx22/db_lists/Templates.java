package com.robertx22.db_lists;

import com.robertx22.mmorpg.Ref;
import com.robertx22.world_gen.types.FeatureType;
import net.minecraft.util.ResourceLocation;

public class Templates {

    public static final FeatureType bigWoodPillar = new FeatureType(new ResourceLocation(Ref.MODID, "big_wood_pillar0"));
    public static final FeatureType smallWoodPillar = new FeatureType(new ResourceLocation(Ref.MODID, "small_wood_pillar0"));
    public static final FeatureType smallTreasure0 = new FeatureType(new ResourceLocation(Ref.MODID, "small_treasure0"))
            .lowerBy(2);

}
