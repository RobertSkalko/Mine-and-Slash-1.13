package com.robertx22.uncommon.item_filters;

import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.item_filters.bases.ItemFilter;
import net.minecraft.item.ItemStack;

public class GearItemFilter extends ItemFilter {

    @Override
    public boolean IsValidItem(ItemStack stack) {
        return Gear.Load(stack) != null;
    }
}
