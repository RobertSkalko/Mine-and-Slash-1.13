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
    public static final ItemFilterGroup ANY_UNIQUE = new ItemFilterGroup(new UniqueItemFilter());
    public static final ItemFilterGroup ANY_GEAR = new ItemFilterGroup(new GearItemFilter());
    public static final ItemFilterGroup ANY_GEAR_EXCEPT_UNIQUE = new ItemFilterGroup(new GearExceptUniqueFilter());
    public static final ItemFilterGroup ANY_SPELL = new ItemFilterGroup(new SpellItemFilter());
    public static final ItemFilterGroup ANY_RUNE = new ItemFilterGroup(new RuneItemFilter());
    public static final ItemFilterGroup ANY_MASTER_BAG = new ItemFilterGroup(Arrays.asList(new MapItemFilter(), new CurrencyItemFilter(), new RuneItemFilter(), new GearItemFilter(), new SpellItemFilter()));

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
