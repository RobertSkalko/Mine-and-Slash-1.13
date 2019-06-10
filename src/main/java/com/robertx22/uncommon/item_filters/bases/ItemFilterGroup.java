package com.robertx22.uncommon.item_filters.bases;

import com.robertx22.uncommon.item_filters.*;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemFilterGroup {

    public static final ItemFilterGroup LOOT_BAG_FILTER = new ItemFilterGroup(Arrays.asList(new GearItemFilter(), new RuneItemFilter(), new SpellItemFilter()));
    public static final ItemFilterGroup ANY_MAP = new ItemFilterGroup(new MapItemFilter());
    public static final ItemFilterGroup ANY_CURRENCY = new ItemFilterGroup(new CurrencyItemFilter());

    List<ItemFilter> filters = new ArrayList<>();

    public ItemFilterGroup(ItemFilter filter) {
        this.filters.add(filter);
    }

    public ItemFilterGroup(List<ItemFilter> filters) {
        this.filters.addAll(filters);
    }

    public boolean anyMatchesFilter(ItemStack stack) {

        for (ItemFilter filter : filters) {
            if (filter.IsValidItem(stack)) {
                return true;
            }
        }

        return false;
    }

}
