package com.robertx22.dimensions.world_providers;

import java.util.function.Function;

import com.robertx22.dimensions.BaseWorldProvider;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.CLOC;

import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

public class DesertHillsIWP extends BaseWorldProvider {

    public DesertHillsIWP(DimensionType type) {
	super(type);
    }

    @Override
    public String GUID() {
	return "deserthills";
    }

    @Override
    public String unlocString() {
	return CLOC.word("desert_hills");
    }

    @Override
    public Biome getBiome() {
	return Biomes.DESERT_HILLS;
    }

    public static ModDimension newModDimension(String name) {
	return new ModDimension() {
	    @Override
	    public Function<DimensionType, ? extends net.minecraft.world.dimension.Dimension> getFactory() {
		return DesertHillsIWP::new;
	    }
	}.setRegistryName(new ResourceLocation(Ref.MODID, name));
    }

}