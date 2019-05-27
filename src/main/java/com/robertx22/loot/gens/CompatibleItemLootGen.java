package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.config.compatible_items.ConfigItem;
import com.robertx22.config.compatible_items.ConfigItems;
import com.robertx22.loot.LootInfo;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Collectors;

public class CompatibleItemLootGen extends BaseLootGen {

    public CompatibleItemLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.COMPATIBLE_ITEMS_DROPRATE.get().floatValue();
    }

    @Override
    public boolean condition() {

        return ModConfig.INSTANCE.Server.USE_COMPATIBILITY_ITEMS.get();
    }

    @Override
    public ItemStack generateOne() {

        if (ModConfig.INSTANCE.Server.USE_COMPATIBILITY_ITEMS.get() == true && info.mobData != null) {

            return gen(info.mobData.getLevel());
        }

        return ItemStack.EMPTY;

    }

    public static ItemStack gen(int level) {

        ConfigItem config = RandomUtils.weightedRandom(ConfigItems.INSTANCE.getAll()
                .stream()
                .filter(x -> x.statsAddedOnlyOnDrop == false)
                .collect(Collectors.toList()));

        if (config != null) {
            ResourceLocation res = new ResourceLocation(config.registryName);

            if (ForgeRegistries.ITEMS.containsKey(res)) {

                ItemStack stack = new ItemStack(ForgeRegistries.ITEMS.getValue(res));

                return config.create(stack, level);
            }
        }

        return ItemStack.EMPTY;
    }

}