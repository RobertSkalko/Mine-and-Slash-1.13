package com.robertx22.loot.gens;

import com.robertx22.config.ModConfig;
import com.robertx22.items.currency.CurrencyItem;
import com.robertx22.loot.LootInfo;
import com.robertx22.uncommon.interfaces.ITiered;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class CurrencyLootGen extends BaseLootGen {

    public CurrencyLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.CURRENCY_DROPRATE.get().floatValue();

    }

    @Override
    public ItemStack generateOne() {

        return new ItemStack((Item) RandomUtils.WeightedRandom(ListUtils.SameTierOrLess(new ArrayList<ITiered>(CurrencyItem.ITEMS), info.tier)));

    }

}
