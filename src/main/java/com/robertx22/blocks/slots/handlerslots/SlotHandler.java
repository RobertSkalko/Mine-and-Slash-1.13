package com.robertx22.blocks.slots.handlerslots;

import com.robertx22.items.bags.BaseSlot;
import com.robertx22.uncommon.item_filters.bases.ItemFilterGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class SlotHandler extends BaseSlot {

    ItemFilterGroup filter;

    public SlotHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition,
                       ItemFilterGroup filter) {
        super(itemHandler, index, xPosition, yPosition);
        this.filter = filter;

    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return filter.anyMatchesFilter(stack);
    }

}
