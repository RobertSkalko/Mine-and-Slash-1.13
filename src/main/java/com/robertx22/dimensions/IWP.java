package com.robertx22.dimensions;

import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.interfaces.ILocName;
import com.robertx22.uncommon.interfaces.IWeighted;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ModDimension;

public interface IWP extends IWeighted, ILocName {
    abstract String GUID();

    abstract ModDimension newModDimension();

    //public BaseWorldProvider newInstance(DimensionType type);

    abstract Biome getBiome();

    public default ITextComponent locName() {
        return CLOC.word(this.GUID().toLowerCase());
    }
}
