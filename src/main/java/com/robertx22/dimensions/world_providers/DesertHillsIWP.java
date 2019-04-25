package com.robertx22.dimensions.world_providers;

import com.robertx22.dimensions.BaseWorldProvider;
import com.robertx22.uncommon.CLOC;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

public class DesertHillsIWP extends BaseWorldProvider {

    public DesertHillsIWP() {

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

}