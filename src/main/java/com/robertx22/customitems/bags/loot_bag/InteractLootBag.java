package com.robertx22.customitems.bags.loot_bag;

import com.robertx22.mmorpg.Ref;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IInteractionObject;

public class InteractLootBag implements IInteractionObject {

    private final ItemStack stack;

    public InteractLootBag(ItemStack stack) {
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
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
	return new ContainerLootBag(playerInventory, new InventoryLootBag(stack));

    }

    @Override
    public String getGuiID() {
	return Ref.MODID + ":loot_bag";
    }

}
