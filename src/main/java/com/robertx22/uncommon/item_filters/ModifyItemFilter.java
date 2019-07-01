package com.robertx22.uncommon.item_filters;

import com.robertx22.items.currency.ICurrencyItemEffect;
import com.robertx22.uncommon.item_filters.bases.ItemFilter;
import net.minecraft.item.ItemStack;

public class ModifyItemFilter extends ItemFilter {

    @Override
    public boolean IsValidItem(ItemStack stack) {
        return stack.getItem() instanceof ICurrencyItemEffect;
    }
}

