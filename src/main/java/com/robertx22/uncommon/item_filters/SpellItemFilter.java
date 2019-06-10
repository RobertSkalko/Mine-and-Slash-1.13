package com.robertx22.uncommon.item_filters;

import com.robertx22.uncommon.datasaving.Spell;
import com.robertx22.uncommon.item_filters.bases.ItemFilter;
import net.minecraft.item.ItemStack;

public class SpellItemFilter extends ItemFilter {

    @Override
    public boolean IsValidItem(ItemStack stack) {
        return Spell.Load(stack) != null;
    }
}
