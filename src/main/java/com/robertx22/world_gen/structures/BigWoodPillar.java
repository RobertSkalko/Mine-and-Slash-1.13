package com.robertx22.world_gen.structures;

import com.robertx22.mmorpg.Ref;
import net.minecraft.util.ResourceLocation;

public class BigWoodPillar extends SmallDecoration {

    static final ResourceLocation loc = new ResourceLocation(Ref.MODID, "big_wood_pillar0");

    public BigWoodPillar() {
        super("big_wood_pillar", loc);
    }

}
