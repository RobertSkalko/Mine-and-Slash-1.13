package com.robertx22.database.world_providers;

import com.robertx22.database.map_affixes.beneficial.BonusHealthAffix;
import com.robertx22.saveclasses.mapitem.MapAffixData;
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
    public List<String> smallSurfaceDecorations() {
        return Arrays.asList();
    }

    @Override
    public List<MapAffixData> getMapAffixes() {
        return Arrays.asList(new MapAffixData(new BonusHealthAffix(), 100));
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