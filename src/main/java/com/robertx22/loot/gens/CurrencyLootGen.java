package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.items.currency.CurrencyItem;
import com.robertx22.loot.LootInfo;
import com.robertx22.uncommon.enumclasses.LootType;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;

public class CurrencyLootGen extends BaseLootGen {

    public CurrencyLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.CURRENCY_DROPRATE.get().floatValue();

    }

    @Override
    public LootType lootType() {
        return LootType.Currency;
    }

    @Override
    public boolean condition() {
        return info.level >= ModConfig.INSTANCE.Server.CURRENCY_DROP_AFTER_LEVEL.get();
    }

    @Override
    public ItemStack generateOne() {

        return new ItemStack(RandomUtils.weightedRandom(ListUtils.SameTierOrLess(CurrencyItem.ITEMS, info.tier)));

    }

}
