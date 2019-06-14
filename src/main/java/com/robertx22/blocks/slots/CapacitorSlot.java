package com.robertx22.blocks.slots;

import com.robertx22.items.misc.ItemCapacitor;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class CapacitorSlot extends Slot {
    public CapacitorSlot(IInventory inventoryIn, int index, int xPosition,
                         int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof ItemCapacitor;
    }
}
