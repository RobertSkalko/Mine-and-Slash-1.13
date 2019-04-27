package com.robertx22.dimensions;

import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.interfaces.IWeighted;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ModDimension;

public interface IWP extends IWeighted {
    abstract String GUID();

    abstract String unlocString();

    abstract ModDimension newModDimension(ResourceLocation res);

    abstract Biome getBiome();

    public default String locName() {
	return CLOC.word(unlocString());
    }
}
