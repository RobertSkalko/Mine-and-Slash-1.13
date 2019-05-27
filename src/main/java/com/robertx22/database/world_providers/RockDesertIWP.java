package com.robertx22.database.world_providers;

import com.robertx22.database.map_affixes.beneficial.ele_res.BonusFireResistAffix;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.Arrays;
import java.util.List;
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
    public List<String> smallSurfaceDecorations() {
        return Arrays.asList();
    }

    @Override
    public List<MapAffixData> getMapAffixes() {
        return Arrays.asList(new MapAffixData(new BonusFireResistAffix(), 100));
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