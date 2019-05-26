package com.robertx22.world_gen.configs;

import net.minecraft.world.gen.placement.IPlacementConfig;

// 0 is none, 100 is all the time (once per chunk)
public class MyChanceConfig implements IPlacementConfig {
    public final float chance;

    public MyChanceConfig(float chance) {
        this.chance = chance;
    }
}
