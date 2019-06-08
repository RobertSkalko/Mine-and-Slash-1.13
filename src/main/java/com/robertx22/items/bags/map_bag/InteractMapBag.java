package com.robertx22.items.bags.map_bag;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;

public class InteractMapBag implements INamedContainerProvider {

    private final ItemStack stack;

    public InteractMapBag(ItemStack stack) {
        this.stack = stack;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory inventory,
                                PlayerEntity player) {
        return new ContainerMapBag(inventory, new InventoryMapBag(stack));
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Map Bag");
    }

}
