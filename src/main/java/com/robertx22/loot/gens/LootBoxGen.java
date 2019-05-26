package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.items.misc.ItemLootbox;
import com.robertx22.loot.LootInfo;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LootBoxGen extends BaseLootGen {

    public LootBoxGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.LOOTBOX_DROPRATE.get().floatValue();

    }

    @Override
    public ItemStack generateOne() {

        return new ItemStack((Item) RandomUtils.WeightedRandom(ListUtils.CollectionToList(ItemLootbox.Items
                .values())));

    }

}