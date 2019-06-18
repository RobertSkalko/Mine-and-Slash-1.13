package com.robertx22.database.world_providers;

import com.robertx22.database.map_affixes.beneficial.ele_dmg.BonusFireDamageAffix;
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

public class DesertHillsIWP extends BaseWorldProvider {

    public DesertHillsIWP(World world, DimensionType type) {
        super(world, type);
    }

    @Override
    public String GUID() {
        return "desert_hills";
    }

    @Override
    public List<MapAffixData> getMapAffixes() {
        return Arrays.asList(new MapAffixData(new BonusFireDamageAffix(), 100));
    }

    @Override
    public HashMap<Block, Block> blocksToReplace() {
        HashMap<Block, Block> map = new HashMap<>();
        map.put(Blocks.OAK_LOG, Blocks.SANDSTONE);
        map.put(Blocks.OAK_STAIRS, Blocks.SANDSTONE_STAIRS);

        return map;
    }

    @Override
    public Biome getBiome() {
        return Biomes.DESERT_HILLS;
    }

    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> classFactory() {
        return DesertHillsIWP::new;
    }

    @Override
    public String locNameForLangFile() {
        return "Desert Hills";
    }
}