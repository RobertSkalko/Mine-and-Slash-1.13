package com.robertx22.uncommon.item_filters;

import com.robertx22.uncommon.datasaving.Rune;
import com.robertx22.uncommon.item_filters.bases.ItemFilter;
import net.minecraft.item.ItemStack;

public class RuneItemFilter extends ItemFilter {

    @Override
    public boolean IsValidItem(ItemStack stack) {
        return Rune.Load(stack) != null;
    }
}
