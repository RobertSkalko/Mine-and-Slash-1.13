package com.robertx22.mmorpg.proxy;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CommonProxy implements IProxy {

    @Override
    public void preInit(FMLCommonSetupEvent event) {

    }

    public static void genOre(Block block, int amount) {

	BiomeManager.getBiomes(BiomeManager.BiomeType.WARM)
		.forEach((BiomeManager.BiomeEntry biomeEntry) -> biomeEntry.biome.addFeature(
			GenerationStage.Decoration.UNDERGROUND_ORES,
			Biome.createCompositeFeature(Feature.MINABLE,
				new MinableConfig(MinableConfig.IS_ROCK, block.getDefaultState(), 12),
				Biome.COUNT_RANGE, new CountRangeConfig(amount, 30, 40, 70)

			)));

    }

    @Override
    public void postInit(InterModProcessEvent event) {

    }

    @Override
    public void loadComplete(FMLLoadCompleteEvent event) {

    }

    @Override
    public String translate(String str) {
	return "";
    }

    @Override
    public EntityPlayer getPlayerEntityFromContext(Supplier<Context> ctx) {
	return null;
    }

}
