package com.robertx22.mmorpg.registers.common;

import com.robertx22.config.ModConfig;
import com.robertx22.items.ores.ItemOre;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.CompositeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;

public class OreGenRegister {
    public static void register() {

        if (ModConfig.INSTANCE.Server.GENERATE_ORES.get()) {

            int amount = 3;

            for (int i = 0; i < ItemOre.Blocks.values().size(); i++) {

                if (amount < 1) {
                    amount = 1;
                }

                genOre(ItemOre.Blocks.get(i), amount--);
            }

        }
    }

    public static void genOre(Block block, int amount) {

        CountRangeConfig countConfig = new CountRangeConfig(amount, 0, 0, 60);
        MinableConfig minableConfig = new MinableConfig(MinableConfig.IS_ROCK, block.getDefaultState(), 8);
        CompositeFeature<MinableConfig, CountRangeConfig> feature = Biome.createCompositeFeature(Feature.MINABLE, minableConfig, Biome.COUNT_RANGE, countConfig);

        for (Biome biome : Biome.BIOMES) {
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, feature);
        }

    }

}
