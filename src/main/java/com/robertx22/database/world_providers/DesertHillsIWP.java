package com.robertx22.database.world_providers;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;

import java.util.function.Function;

public class DesertHillsIWP extends BaseWorldProvider {

    public DesertHillsIWP() {
        super();
    }

    public DesertHillsIWP(DimensionType type) {
        super(type);
    }

    @Override
    public String GUID() {
        return "desert_hills";
    }

    @Override
    public Biome getBiome() {
        return Biomes.DESERT_HILLS;
    }

    @Override
    public Function<DimensionType, ? extends net.minecraft.world.dimension.Dimension> classFactory() {
        return DesertHillsIWP::new;
    }

}