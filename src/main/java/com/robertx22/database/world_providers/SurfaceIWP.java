package com.robertx22.database.world_providers;

import com.robertx22.database.map_mods.bonus.ele_res.BonusFireResistMap;
import com.robertx22.database.map_mods.bonus.ele_res.BonusWaterResistMap;
import com.robertx22.database.stats.StatMod;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class SurfaceIWP extends BaseWorldProvider {

    public SurfaceIWP() {
        super();
    }

    public SurfaceIWP(DimensionType type) {
        super(type);
    }

    @Override
    public String GUID() {
        return "surface";
    }

    @Override
    public List<StatMod> getBonusMobStats() {
        return Arrays.asList(new BonusFireResistMap(), new BonusWaterResistMap());
    }

    @Override
    public Biome getBiome() {
        return Biomes.DEFAULT;
    }

    @Override
    public Function<DimensionType, ? extends Dimension> classFactory() {
        return SurfaceIWP::new;
    }

}