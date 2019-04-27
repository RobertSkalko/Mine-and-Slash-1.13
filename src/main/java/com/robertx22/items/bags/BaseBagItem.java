package com.robertx22.items.bags;

import javax.annotation.Nonnull;

import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.items.ItemSingle;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketCollectItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

@EventBusSubscriber
public abstract class BaseBagItem extends Item {

    public abstract int GuiNumber();

    public abstract boolean IsValidItem(ItemStack stack);

    public static int size = 9 * 6;

    private static final String TAG_ITEMS = "InvItems";

    public BaseBagItem(String name) {

	super(new ItemSingle().group(CreativeTabs.MyModTab));
	RegisterItemUtils.RegisterItemName(this, name);

    }

    @Nonnull
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound oldCapNbt) {
	return new InvProvider();
    }

    private static class InvProvider implements ICapabilitySerializable<INBTBase> {

	private final IItemHandler inv = new ItemStackHandler(size);
	private final LazyOptional<IItemHandler> opt = LazyOptional.of(() -> inv);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, EnumFacing side) {
	    if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, opt);
	    else
		return null;
	}

	@Override
	public INBTBase serializeNBT() {
	    return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(inv, null);
	}

	@Override
	public void deserializeNBT(INBTBase nbt) {
	    CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(inv, null, nbt);

	}

    }

// I THINK ITS NOT NEEDED
    /*
     * @Override public void inventoryTick(ItemStack stack, World world, Entity
     * entityIn, int slot, boolean selected) { if (stack.getTag() != null &&
     * stack.getTag().contains(TAG_ITEMS)) { NBTTagList oldData =
     * stack.getTag().getList(TAG_ITEMS, Constants.NBT.TAG_COMPOUND); IItemHandler
     * newInv = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
     * null).orElse(null);
     * 
     * if (newInv != null) {
     * CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(newInv, null, oldData);
     * 
     * stack.getTag().remove(TAG_ITEMS);
     * 
     * if (stack.getTag().size() == 0) stack.setTag(null); } } }
     */

    @SubscribeEvent
    public static void onPickupItem(EntityItemPickupEvent event) {

	ItemStack stack = event.getItem().getItem();

	for (int i = 0; i < event.getEntityPlayer().inventory.getSizeInventory(); i++) {
	    if (i == event.getEntityPlayer().inventory.currentItem)
		continue; // prevent item deletion

	    ItemStack bag = event.getEntityPlayer().inventory.getStackInSlot(i);
	    if (!bag.isEmpty() && bag.getItem() instanceof BaseBagItem) {
		IItemHandler bagInv = bag.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
			.orElseGet(null);
		if (bagInv == null) {
		    return;
		}

		BaseBagItem bagitem = (BaseBagItem) bag.getItem();

		if (bagitem.IsValidItem(stack) && stack.getCount() > 0) {

		    for (int x = 0; x < bagInv.getSlots(); x++) {
			ItemStack result = bagInv.insertItem(x, stack, false);
			int numPickedUp = stack.getCount() - result.getCount();

			event.getItem().setItem(result);

			if (numPickedUp > 0) {
			    event.setCanceled(true);
			    if (!event.getItem().isSilent()) {
				event.getItem().world
					.playSound(null, event.getEntityPlayer().posX, event.getEntityPlayer().posY,
						event.getEntityPlayer().posZ, SoundEvents.ENTITY_ITEM_PICKUP,
						SoundCategory.PLAYERS, 0.2F,
						((event.getItem().world.rand.nextFloat()
							- event.getItem().world.rand.nextFloat()) * 0.7F + 1.0F)
							* 2.0F);
			    }
			    ((EntityPlayerMP) event.getEntityPlayer()).connection.sendPacket(new SPacketCollectItem(
				    event.getItem().getEntityId(), event.getEntityPlayer().getEntityId(), numPickedUp));
			    event.getEntityPlayer().openContainer.detectAndSendChanges();

			    return;
			}
		    }
		}
	    }
	}
    }

    @Override
    public EnumActionResult onItemUse(ItemUseContext context) {
	World world = context.getWorld();

	TileEntity tile = world.getTileEntity(context.getPos());
	if (tile != null) {
	    if (!world.isRemote) {
		IItemHandler tileInv = null;
		if (tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).isPresent())
		    tileInv = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
		else if (tile instanceof IInventory)
		    tileInv = new InvWrapper((IInventory) tile);

		if (tileInv == null)
		    return EnumActionResult.FAIL;

		IItemHandlerModifiable bagInv = (IItemHandlerModifiable) context.getItem()
			.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

		for (int i = 0; i < bagInv.getSlots(); i++) {
		    ItemStack flower = bagInv.getStackInSlot(i);
		    bagInv.setStackInSlot(i, ItemHandlerHelper.insertItemStacked(tileInv, flower, false));
		}
	    }

	    return EnumActionResult.SUCCESS;
	}
	return EnumActionResult.PASS;
    }

}