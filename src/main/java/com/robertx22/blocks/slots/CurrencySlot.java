package com.robertx22.blocks.slots;

import com.robertx22.uncommon.item_filters.bases.ItemFilterGroup;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class CurrencySlot extends Slot {
    public CurrencySlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return ItemFilterGroup.ANY_CURRENCY.anyMatchesFilter(stack);
    }
}