package com.robertx22.mmorpg.registers.common;

import com.robertx22.config.ModConfig;
import com.robertx22.items.ores.ItemOre;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraftforge.common.BiomeManager;

import java.util.Objects;

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

        CountRangeConfig count = new CountRangeConfig(amount, 0, 0, 60);

        for (BiomeManager.BiomeType biomeType : BiomeManager.BiomeType.values()) {
            for (BiomeManager.BiomeEntry biomeEntry : Objects.requireNonNull(BiomeManager.getBiomes(biomeType))) {
                biomeEntry.biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome
                        .createCompositeFeature(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, block
                                .getDefaultState(), 8), Biome.COUNT_RANGE, count

                        ));
            }
        }
    }

}
