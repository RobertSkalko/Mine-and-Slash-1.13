package com.robertx22.items.spells.nova;

import com.robertx22.items.spells.BaseSpellItem;
import net.minecraft.item.ItemStack;

public abstract class BaseItemNova extends BaseSpellItem {

    public BaseItemNova() {
        super();

    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return super.getUseDuration(stack) / 2;
    }

}