package com.robertx22.database.world_providers;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.function.Function;

public class JungleIWP extends BaseWorldProvider {

    public JungleIWP() {
        super();
    }

    public JungleIWP(DimensionType type) {
        super(type);
    }

    @Override
    public String GUID() {
        return "jungle";
    }

    @Override
    public Biome getBiome() {
        return Biomes.JUNGLE_HILLS;
    }

    @Override
    public Function<DimensionType, ? extends Dimension> classFactory() {
        return JungleIWP::new;
    }

}