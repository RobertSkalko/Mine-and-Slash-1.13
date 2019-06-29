package com.robertx22.uncommon.item_filters;

import com.robertx22.items.currency.CurrencyItem;
import com.robertx22.items.currency.ICurrencyItemEffect;
import com.robertx22.items.ores.ItemOre;
import com.robertx22.items.runes.base.BaseRuneItem;
import com.robertx22.uncommon.item_filters.bases.ItemFilter;
import net.minecraft.item.ItemStack;

public class CurrencyItemFilter extends ItemFilter {

    @Override
    public boolean IsValidItem(ItemStack stack) {

        if (stack.getItem() instanceof BaseRuneItem) {
            return false;
        }

        return stack.getItem() instanceof ICurrencyItemEffect || stack.getItem() instanceof CurrencyItem || stack
                .getItem() instanceof ItemOre;
    }
}

