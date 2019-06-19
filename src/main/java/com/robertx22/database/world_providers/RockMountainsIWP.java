package com.robertx22.database.world_providers;

import com.robertx22.database.map_affixes.beneficial.BonusHealthAffix;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.world_gen.biome_color_schemes.bases.BiomeColorTheme;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class RockMountainsIWP extends BaseWorldProvider {

    public RockMountainsIWP(World world, DimensionType type) {
        super(world, type);
    }

    @Override
    public String GUID() {
        return "rock_mountains";
    }

    @Override
    public BiomeColorTheme biomeTheme() {
        return BiomeColorTheme.STONE;
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
    public BiFunction<World, DimensionType, ? extends Dimension> classFactory() {
        return RockMountainsIWP::new;
    }

    @Override
    public String locNameForLangFile() {
        return "Rocky Mountains";
    }
}