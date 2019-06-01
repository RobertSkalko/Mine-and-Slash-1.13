package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.items.misc.ItemLootbox;
import com.robertx22.loot.LootInfo;
import com.robertx22.uncommon.enumclasses.LootType;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
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
    public LootType lootType() {
        return LootType.LootBox;
    }

    @Override
    public ItemStack generateOne() {

        return new ItemStack(RandomUtils.weightedRandom(ItemLootbox.Items.values()));

    }

}