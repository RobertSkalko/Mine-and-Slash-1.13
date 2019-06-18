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

public class RockDesertIWP extends BaseWorldProvider {

    public RockDesertIWP(World world, DimensionType type) {
        super(world, type);
    }

    @Override
    public String GUID() {
        return "rock_desert";
    }

    @Override
    public HashMap<Block, Block> blocksToReplace() {
        HashMap<Block, Block> map = new HashMap<>();
        map.put(Blocks.OAK_LOG, Blocks.RED_SANDSTONE);
        map.put(Blocks.OAK_PLANKS, Blocks.SMOOTH_RED_SANDSTONE);
        map.put(Blocks.OAK_STAIRS, Blocks.RED_SANDSTONE_STAIRS);

        return map;
    }

    @Override
    public List<MapAffixData> getMapAffixes() {
        return Arrays.asList(new MapAffixData(new BonusFireDamageAffix(), 100));
    }

    @Override
    public Biome getBiome() {
        return Biomes.MODIFIED_BADLANDS_PLATEAU;
    }

    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> classFactory() {
        return RockDesertIWP::new;
    }

    @Override
    public String locNameForLangFile() {
        return "Rock Desert";
    }
}