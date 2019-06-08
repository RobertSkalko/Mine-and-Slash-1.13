package com.robertx22.items.bags.loot_bag;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IInteractionObject;

public class InteractLootBag implements IInteractionObject {

    private final ItemStack stack;

    public InteractLootBag(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public ITextComponent getName() {
        return new StringTextComponent(this.getGuiID());
    }

    @Override
    public boolean hasCustomName() {

        return false;
    }

    @Override
    public ITextComponent getCustomName() {

        return new StringTextComponent(this.getGuiID());
    }

    @Override
    public Container createContainer(PlayerInventory playerInventory,
                                     PlayerEntity playerIn) {
        return new ContainerLootBag(playerInventory, new InventoryLootBag(stack));

    }

    @Override
    public String getGuiID() {
        return ItemLootBag.ID;
    }

}
