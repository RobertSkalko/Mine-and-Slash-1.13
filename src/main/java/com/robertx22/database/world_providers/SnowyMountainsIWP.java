package com.robertx22.database.world_providers;

import com.robertx22.database.map_affixes.beneficial.ele_dmg.BonusWaterDamageAffix;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.Arrays;
import java.util.List;
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
    public List<String> smallSurfaceDecorations() {
        return Arrays.asList();
    }

    @Override
    public List<MapAffixData> getMapAffixes() {
        return Arrays.asList(new MapAffixData(new BonusWaterDamageAffix(), 100));
    }

    @Override
    public Biome getBiome() {
        return Biomes.SNOWY_MOUNTAINS;
    }

    @Override
    public Function<DimensionType, ? extends Dimension> classFactory() {
        return SnowyMountainsIWP::new;
    }

    @Override
    public String locNameForLangFile() {
        return "Snowy Mountains";
    }
}
