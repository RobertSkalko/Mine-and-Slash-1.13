package com.robertx22.items.bags.currency_bag;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IInteractionObject;

public class InteractCurrencyBag implements IInteractionObject {

    private final ItemStack stack;

    public InteractCurrencyBag(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public ITextComponent getName() {
        return new TextComponentString(this.getGuiID());
    }

    @Override
    public boolean hasCustomName() {

        return false;
    }

    @Override
    public ITextComponent getCustomName() {

        return new TextComponentString(this.getGuiID());
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory,
                                     EntityPlayer playerIn) {
        return new ContainerCurrencyBag(playerInventory, new InventoryCurrencyBag(stack));

    }

    @Override
    public String getGuiID() {
        return ItemCurrencyBag.ID;
    }

}
