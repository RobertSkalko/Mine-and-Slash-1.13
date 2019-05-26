package com.robertx22.dimensions.world_providers;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.function.Function;

public class RockMountainsIWP extends BaseWorldProvider {

    public RockMountainsIWP() {
        super();
    }

    public RockMountainsIWP(DimensionType type) {
        super(type);
    }

    @Override
    public String GUID() {
        return "rock_mountains";
    }

    @Override
    public Biome getBiome() {
        return Biomes.GRAVELLY_MOUNTAINS;
    }

    @Override
    public Function<DimensionType, ? extends Dimension> classFactory() {
        return RockMountainsIWP::new;
    }

}