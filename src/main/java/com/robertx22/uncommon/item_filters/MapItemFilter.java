package com.robertx22.uncommon.item_filters;

import com.robertx22.uncommon.datasaving.Map;
import com.robertx22.uncommon.item_filters.bases.ItemFilter;
import net.minecraft.item.ItemStack;

public class MapItemFilter extends ItemFilter {

    @Override
    public boolean IsValidItem(ItemStack stack) {
        return Map.has(stack);
    }
}
