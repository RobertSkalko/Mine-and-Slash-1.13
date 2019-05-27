package com.robertx22.database.world_providers;

import com.robertx22.database.map_affixes.beneficial.ele_dmg.BonusFireDamageAffix;
import com.robertx22.database.map_affixes.beneficial.ele_res.BonusFireResistAffix;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class NetherIWP extends BaseWorldProvider {

    public NetherIWP() {
        super();
    }

    public NetherIWP(DimensionType type) {
        super(type);
    }

    @Override
    public String GUID() {
        return "nether";
    }

    @Override
    public List<MapAffixData> getMapAffixes() {
        return Arrays.asList(new MapAffixData(new BonusFireDamageAffix(), 80), new MapAffixData(new BonusFireResistAffix(), 100));
    }

    @Override
    public float getBonusLootMulti() {
        return 1.2F;
    }

    @Override
    public Biome getBiome() {
        return Biomes.NETHER;
    }

    @Override
    public Function<DimensionType, ? extends Dimension> classFactory() {
        return NetherIWP::new;
    }

}