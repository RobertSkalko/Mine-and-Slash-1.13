package com.robertx22.database.world_providers;

import com.robertx22.db_lists.bases.IBonusLootMulti;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.interfaces.IAutoLocName;
import com.robertx22.uncommon.interfaces.IWeighted;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import com.robertx22.world_gen.types.FeatureType;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ModDimension;

import java.util.HashMap;
import java.util.List;

public interface IWP extends IWeighted, IAutoLocName, IBonusLootMulti {
    abstract String GUID();

    ModDimension getModDim();

    ResourceLocation getResourceLoc();

    HashMap<Block, Block> blocksToReplace();

    ModDimension newModDimension();

    List<MapAffixData> getMapAffixes(); // missing thunder damage maps.. hmm

    void setModDimension(ModDimension mod);

    @Override
    public default AutoLocGroup locNameGroup() {
        return AutoLocGroup.World_Types;
    }

    Biome getBiome();

    List<FeatureType> smallSurfaceDecorations();

    List<FeatureType> smallTreasures();

    default FeatureType randomSmallTreasure() {
        if (smallTreasures().isEmpty()) {
            return null;
        }
        
        return RandomUtils.weightedRandom(smallTreasures());
    }

    default FeatureType randomSmallSurfaceDecoration() {
        if (smallSurfaceDecorations().isEmpty()) {
            return null;
        }
        return RandomUtils.weightedRandom(smallSurfaceDecorations());
    }

}
