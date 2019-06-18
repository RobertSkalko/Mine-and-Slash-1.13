package com.robertx22.database.world_providers;

import com.robertx22.database.map_affixes.beneficial.ele_dmg.BonusWaterDamageAffix;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;

public class SnowyMountainsIWP extends BaseWorldProvider {

    public SnowyMountainsIWP(World world, DimensionType type) {
        super(world, type);
    }

    @Override
    public String GUID() {
        return "snowy_mountains";
    }

    @Override
    public HashMap<Block, Block> blocksToReplace() {
        HashMap<Block, Block> map = new HashMap<>();
        map.put(Blocks.OAK_LOG, Blocks.SPRUCE_LOG);
        map.put(Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS);
        map.put(Blocks.OAK_STAIRS, Blocks.SPRUCE_STAIRS);

        return map;
    }

    @Override
    public List<MapAffixData> getMapAffixes() {
        return Arrays.asList(new MapAffixData(new BonusWaterDamageAffix(), 100));
    }

    @Override
    public Biome getBiome() {
        return Biomes.SNOWY_MOUNTAINS;
    }

    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> classFactory() {
        return SnowyMountainsIWP::new;
    }

    @Override
    public String locNameForLangFile() {
        return "Snowy Mountains";
    }
}
