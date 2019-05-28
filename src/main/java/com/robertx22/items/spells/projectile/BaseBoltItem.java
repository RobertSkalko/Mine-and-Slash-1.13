package com.robertx22.items.spells.projectile;

import com.robertx22.items.spells.BaseSpellItem;
import net.minecraft.item.ItemStack;

public abstract class BaseBoltItem extends BaseSpellItem {

    public BaseBoltItem() {
        super();

    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return super.getUseDuration(stack) / 2;
    }

}