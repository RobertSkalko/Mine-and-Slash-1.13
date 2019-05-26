package com.robertx22.database.world_providers;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.MajorArmorFlat;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.Arrays;
import java.util.List;
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
    public List<StatMod> getBonusMobStats() {
        return Arrays.asList(new MajorArmorFlat(), new ArmorFlat());
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