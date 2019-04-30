package com.robertx22.dimensions;

import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.interfaces.IWeighted;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ModDimension;

public interface IWP extends IWeighted {
    abstract String GUID();

    abstract String unlocString();

    abstract ModDimension newModDimension();

    abstract Biome getBiome();

    default void setupModDim() {
        this.setModDim(this.newModDimension());
    }

    abstract void setModDim(ModDimension moddim);

    public default ITextComponent locName() {
        return CLOC.word(unlocString());
    }
}
