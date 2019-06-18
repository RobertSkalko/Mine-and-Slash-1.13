package com.robertx22.database.world_providers;

import com.robertx22.database.map_affixes.beneficial.ele_dmg.BonusFireDamageAffix;
import com.robertx22.database.map_affixes.beneficial.ele_res.BonusFireResistAffix;
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

public class NetherIWP extends BaseWorldProvider {

    public NetherIWP(World world, DimensionType type) {
        super(world, type);
    }

    @Override
    public String GUID() {
        return "nether";
    }

    @Override
    public List<MapAffixData> getMapAffixes() {
        return Arrays.asList(new MapAffixData(new BonusFireDamageAffix(), 80), new MapAffixData(new BonusFireResistAffix(), 100));
    }

    @Override
    public HashMap<Block, Block> blocksToReplace() {
        HashMap<Block, Block> map = new HashMap<>();
        map.put(Blocks.OAK_LOG, Blocks.NETHER_BRICKS);
        map.put(Blocks.OAK_STAIRS, Blocks.NETHER_BRICK_STAIRS);

        return map;
    }

    @Override
    public float getBonusLootMulti() {
        return 1.2F;
    }

    @Override
    public Biome getBiome() {
        return Biomes.NETHER;
    }

    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> classFactory() {
        return NetherIWP::new;
    }

    @Override
    public String locNameForLangFile() {
        return "Hellscape";
    }
}