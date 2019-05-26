package com.robertx22.database.world_providers;

import com.robertx22.database.map_mods.bonus.ele_dmg.BonusNatureDamageMap;
import com.robertx22.database.stats.StatMod;
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
    public List<StatMod> getBonusMobStats() {
        return Arrays.asList(new BonusNatureDamageMap());
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