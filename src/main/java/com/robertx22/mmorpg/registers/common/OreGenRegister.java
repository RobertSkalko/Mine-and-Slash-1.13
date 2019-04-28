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

public class OreGenRegister {
    public static void register() {

        if (ModConfig.INSTANCE.Server.get().GENERATE_ORES.get()) {

            int amount = 7;

            for (int i = 0; i < ItemOre.Blocks.values().size(); i++) {
                genOre(ItemOre.Blocks.get(i), amount--);
            }

        }
    }

    public static void genOre(Block block, int amount) {

        BiomeManager.getBiomes(BiomeManager.BiomeType.WARM)
                .forEach((BiomeManager.BiomeEntry biomeEntry) -> biomeEntry.biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome
                        .createCompositeFeature(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, block
                                .getDefaultState(), 12), Biome.COUNT_RANGE, new CountRangeConfig(amount, 30, 40, 70)

                        )));

    }

}
