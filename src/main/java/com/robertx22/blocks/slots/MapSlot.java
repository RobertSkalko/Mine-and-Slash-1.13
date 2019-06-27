package com.robertx22.blocks.slots;

import com.robertx22.items.bags.BaseSlot;
import com.robertx22.uncommon.item_filters.bases.ItemFilterGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class MapSlot extends BaseSlot {

    public MapSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);

    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return ItemFilterGroup.ANY_MAP.anyMatchesFilter(stack);
    }

}