package com.robertx22.dimensions.world_providers;

import com.robertx22.dimensions.BaseWorldProvider;
import com.robertx22.mmorpg.Ref;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

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
    public Biome getBiome() {
        return Biomes.DESERT_HILLS;
    }

    @Override
    protected ModDimension newModDimension() {
        return new ModDimension() {
            @Override
            public Function<DimensionType, ? extends net.minecraft.world.dimension.Dimension> getFactory() {
                return DesertHillsIWP::new;
            }
        }.setRegistryName(new ResourceLocation(Ref.MODID, "dim_" + this.GUID()));
    }

}