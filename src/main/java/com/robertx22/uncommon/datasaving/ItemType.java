package com.robertx22.uncommon.datasaving;

import com.robertx22.uncommon.item_filters.bases.ItemFilterGroup;
import com.robertx22.uncommon.localization.Styles;
import com.robertx22.uncommon.localization.Words;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public enum ItemType {

    GEAR(ItemFilterGroup.ANY_GEAR, Words.Gears),
    NONE(null, Words.None);

    ItemType(ItemFilterGroup filter, Words word) {
        this.filter = filter;
        this.word = word;
    }

    public boolean isType(ItemStack stack) {
        if (filter == null) {
            return false;
        }
        return filter.anyMatchesFilter(stack);
    }

    private ItemFilterGroup filter;
    public Words word;

    public static ITextComponent getTooltipString(List<ItemType> types) {

        ITextComponent comp = Styles.LIGHT_PURPLECOMP()
                .appendSibling(Words.UsableOn.locName())
                .appendText(": ");

        int i = 0;
        for (ItemType type : types) {
            if (i != 0 && i != types.size()) {
                comp.appendText(",");
            }
            comp.appendSibling(type.word.locName());
            i++;
        }

        return comp;

    }

    public static ItemType getType(ItemStack stack) {

        for (ItemType type : ItemType.values()) {
            if (type.isType(stack)) {
                return type;
            }
        }
        return NONE;
    }

}