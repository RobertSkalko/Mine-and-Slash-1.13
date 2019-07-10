package com.robertx22.items.bags.master_bag;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class InventoryMasterBag implements IItemHandlerModifiable {

    final public ItemStack bag;
    public ItemStackHandler bagInv;

    public InventoryMasterBag(ItemStack bag, ContainerMasterBag.ItemType type) {
        this.bag = bag;

        this.bagInv = new ItemStackHandler(6 * 9);

        if (bag.getItem() instanceof ItemMasterBag) {
            ItemMasterBag masterBag = (ItemMasterBag) bag.getItem();
            this.bagInv = masterBag.getInventory(bag, type);
        }

    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        bagInv.setStackInSlot(slot, stack);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return bagInv.isItemValid(slot, stack);
    }

    @Override
    public int getSlots() {
        return bagInv.getSlots();
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return bagInv.getStackInSlot(slot);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return bagInv.insertItem(slot, stack, simulate);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return bagInv.extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return bagInv.getSlotLimit(slot);
    }
}