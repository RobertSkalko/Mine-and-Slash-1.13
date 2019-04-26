package com.robertx22.blocks.bases;

import net.minecraft.item.ItemStack;

public interface IOBlock {

    boolean isItemValidInput(ItemStack stack);

    boolean isItemValidOutput(ItemStack stack);

    boolean isAutomatable();

    int[] inputSlots();

}
