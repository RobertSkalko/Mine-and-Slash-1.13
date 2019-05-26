package com.robertx22.dimensions.world_providers;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.function.Function;

public class SnowyMountainsIWP extends BaseWorldProvider {

    public SnowyMountainsIWP() {
        super();
    }

    public SnowyMountainsIWP(DimensionType type) {
        super(type);
    }

    @Override
    public String GUID() {
        return "snowy_mountains";
    }

    @Override
    public Biome getBiome() {
        return Biomes.SNOWY_MOUNTAINS;
    }

    @Override
    public Function<DimensionType, ? extends Dimension> classFactory() {
        return SnowyMountainsIWP::new;
    }

}
