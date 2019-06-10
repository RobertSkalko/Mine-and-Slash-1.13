package com.robertx22.items.bags.currency_bag;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;

public class InteractCurrencyBag implements INamedContainerProvider {

    private final ItemStack stack;

    public InteractCurrencyBag(ItemStack stack) {
        this.stack = stack;
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {
        return new ContainerCurrencyBag(i, inventory, new InventoryCurrencyBag(stack));
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Currency Bag");
    }

}
