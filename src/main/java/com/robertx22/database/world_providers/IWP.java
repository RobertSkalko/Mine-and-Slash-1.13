package com.robertx22.database.world_providers;

import com.robertx22.db_lists.bases.IBonusLootMulti;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.interfaces.IAutoLocName;
import com.robertx22.uncommon.interfaces.IWeighted;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ModDimension;

import java.util.ArrayList;
import java.util.List;

public interface IWP extends IWeighted, IAutoLocName, IBonusLootMulti {
    abstract String GUID();

    ModDimension getModDim();

    ResourceLocation getResourceLoc();

    ModDimension newModDimension();

    List<MapAffixData> getMapAffixes(); // missing thunder damage maps.. hmm

    void setModDimension(ModDimension mod);

    @Override
    public default AutoLocGroup locNameGroup() {
        return AutoLocGroup.World_Types;
    }

    Biome getBiome();

    List<String> smallSurfaceDecorations();

    static List<ResourceLocation> listOfSmallSurfaceDecorations = new ArrayList<>();

    default List<ResourceLocation> getSmallSurfaceDecorations() {

        if (listOfSmallSurfaceDecorations.isEmpty()) {
            for (String str : smallSurfaceDecorations()) {
                listOfSmallSurfaceDecorations.add(new ResourceLocation(Ref.MODID, str));
            }
        }

        return listOfSmallSurfaceDecorations;

    }

    default ResourceLocation randomSmallSurfaceDecoration() {
        if (getSmallSurfaceDecorations().isEmpty()) {
            return null;
        }
        return getSmallSurfaceDecorations().get(RandomUtils.RandomRange(0, getSmallSurfaceDecorations()
                .size()));

    }

}
