package com.robertx22.world_gen.configs;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.world.gen.placement.IPlacementConfig;

// 0 is none, 100 is all the time (once per chunk)
public class MyChanceConfig implements IPlacementConfig {
    public final float chance;

    public MyChanceConfig(float chance) {
        this.chance = chance;
    }

    @Override
    public <T> Dynamic<T> func_214719_a(DynamicOps<T> p_214719_1_) {
        return null; // TODO not sure if this will crash
    }
}
