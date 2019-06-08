package com.robertx22.database.world_providers;

import com.robertx22.database.map_affixes.beneficial.ele_dmg.BonusFireDamageAffix;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;

import java.util.Arrays;
import java.util.List;
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
    public List<MapAffixData> getMapAffixes() {
        return Arrays.asList(new MapAffixData(new BonusFireDamageAffix(), 100));
    }

    @Override
    public List<String> smallSurfaceDecorations() {
        return Arrays.asList();
    }

    @Override
    public Biome getBiome() {
        return Biomes.DESERT_HILLS;
    }

    @Override
    public Function<DimensionType, ? extends net.minecraft.world.dimension.Dimension> classFactory() {
        return DesertHillsIWP::new;
    }

    @Override
    public String locNameForLangFile() {
        return "Desert Hills";
    }
}