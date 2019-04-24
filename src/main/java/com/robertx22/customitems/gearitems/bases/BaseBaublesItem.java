package com.robertx22.customitems.gearitems.bases;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class BaseBaublesItem extends Item implements IGearItem {

    public BaseBaublesItem() {

	super(new Properties().maxStackSize(1));

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
	// CAUSE DUPE GLITCH

	/*
	 * if (!world.isRemote) { IBaublesItemHandler baubles =
	 * BaublesApi.getBaublesHandler(player); for (int i = 0; i < baubles.getSlots();
	 * i++) if ((baubles.getStackInSlot(i) == null ||
	 * baubles.getStackInSlot(i).isEmpty()) && baubles.isItemValidForSlot(i,
	 * player.getHeldItem(hand), player)) { baubles.setStackInSlot(i,
	 * player.getHeldItem(hand).copy()); if (!player.capabilities.isCreativeMode) {
	 * player.inventory.setInventorySlotContents(player.inventory.currentItem,
	 * ItemStack.EMPTY); } onEquipped(player.getHeldItem(hand), player); break; } }
	 */
	return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

}
