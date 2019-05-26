package com.robertx22.database.world_providers;

import com.robertx22.database.map_mods.bonus.ele_dmg.BonusWaterDamageMap;
import com.robertx22.database.stats.StatMod;
import net.minecraft.init.Biomes;
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
    public List<StatMod> getBonusMobStats() {
        return Arrays.asList(new BonusWaterDamageMap());
    }

    @Override
    public Biome getBiome() {
        return Biomes.SNOWY_MOUNTAINS;
    }

    @Override
    public Function<DimensionType, ? extends Dimension> classFactory() {
        return SnowyMountainsIWP::new;
    }

}
