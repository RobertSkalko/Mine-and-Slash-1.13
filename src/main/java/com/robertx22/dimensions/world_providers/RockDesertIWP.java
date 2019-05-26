package com.robertx22.dimensions.world_providers;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.function.Function;

public class RockDesertIWP extends BaseWorldProvider {

    public RockDesertIWP() {
        super();
    }

    public RockDesertIWP(DimensionType type) {
        super(type);
    }

    @Override
    public String GUID() {
        return "rock_desert";
    }

    @Override
    public Biome getBiome() {
        return Biomes.MODIFIED_BADLANDS_PLATEAU;
    }

    @Override
    public Function<DimensionType, ? extends Dimension> classFactory() {
        return RockDesertIWP::new;
    }

}