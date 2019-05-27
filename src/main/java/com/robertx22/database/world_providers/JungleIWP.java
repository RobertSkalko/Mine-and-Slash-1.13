package com.robertx22.database.world_providers;

import com.robertx22.database.map_affixes.beneficial.ele_dmg.BonusNatureDamageAffix;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.Arrays;
import java.util.List;
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
    public List<MapAffixData> getMapAffixes() {
        return Arrays.asList(new MapAffixData(new BonusNatureDamageAffix(), 100));
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