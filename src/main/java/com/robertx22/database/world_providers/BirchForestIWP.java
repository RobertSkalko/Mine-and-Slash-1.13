package com.robertx22.database.world_providers;

import com.robertx22.database.map_affixes.beneficial.ele_res.BonusNatureResistAffix;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static com.robertx22.db_lists.Templates.bigWoodPillar;
import static com.robertx22.db_lists.Templates.smallWoodPillar;

public class BirchForestIWP extends BaseWorldProvider {

    public BirchForestIWP() {
        super();
    }

    public BirchForestIWP(DimensionType type) {
        super(type);
    }

    @Override
    public String GUID() {
        return "birch_forest";
    }

    @Override
    public List<MapAffixData> getMapAffixes() {
        return Arrays.asList(new MapAffixData(new BonusNatureResistAffix(), 100));
    }

    @Override
    public Biome getBiome() {
        return Biomes.TALL_BIRCH_FOREST;
    }

    @Override
    public List<String> smallSurfaceDecorations() {
        return Arrays.asList(bigWoodPillar, smallWoodPillar);
    }

    @Override
    public Function<DimensionType, ? extends Dimension> classFactory() {
        return BirchForestIWP::new;
    }

    @Override
    public String locNameForLangFile() {
        return "Birch Forest";
    }
}