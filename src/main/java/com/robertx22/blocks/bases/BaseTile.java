package com.robertx22.blocks.bases;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;

import javax.annotation.Nullable;
import java.util.Arrays;

public abstract class BaseTile extends TileEntity implements IOBlock, ISidedInventory, ITickable, IInteractionObject {

    public BaseTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    protected ItemStack[] itemStacks;

    public int ticks = 0;
    public short cookTime = 0;
    public int FuelRemaining = 0;

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return this.getName();
    }

    @Override
    public ITextComponent getCustomName() {
        return this.getName();
    }

    // OVERRIDE IF AUTOMATABLE
    @Override
    public int[] inputSlots() {
        return new int[0];
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return slots();
    }

    @Override
    public boolean isItemValidOutput(ItemStack stack) {
        return true;
    }

    private int[] slots() {

        int[] ints = new int[this.itemStacks.length];

        for (int i = 0; i < itemStacks.length; i++) {
            ints[i] = i;
        }

        return ints;
    }

    private boolean containsSlot(int index, int[] slots) {

        for (int i : this.inputSlots()) {
            if (i == index) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {

        if (this.isAutomatable() && containsSlot(index, this.inputSlots())) {
            // don't insert shit
            return this.isItemValidInput(itemStackIn);
        }
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {

        if (this.isAutomatable()) {
            // don't extract stuff that's being processed
            return this.isItemValidInput(stack) == false;
        }
        return false;
    }

    // Gets the stack in the given slot
    @Override
    public ItemStack getStackInSlot(int i) {

        return itemStacks[i];
    }

    /**
     * Removes some of the units from itemstack in the given slot, and returns as a
     * separate itemstack
     *
     * @param slotIndex the slot number to remove the items from
     * @param count     the number of units to remove
     * @return a new itemstack containing the units removed from the slot
     */
    @Override
    public ItemStack decrStackSize(int slotIndex, int count) {
        ItemStack itemStackInSlot = getStackInSlot(slotIndex);
        if (itemStackInSlot.isEmpty())
            return ItemStack.EMPTY; // isEmpty(), EMPTY_ITEM

        ItemStack itemStackRemoved;
        if (itemStackInSlot.getCount() <= count) { // getStackSize
            itemStackRemoved = itemStackInSlot;
            setInventorySlotContents(slotIndex, ItemStack.EMPTY); // EMPTY_ITEM
        } else {
            itemStackRemoved = itemStackInSlot.split(count);
            if (itemStackInSlot.getCount() == 0) { // getStackSize
                setInventorySlotContents(slotIndex, ItemStack.EMPTY); // EMPTY_ITEM
            }
        }
        markDirty();
        return itemStackRemoved;
    }

    // Gets the number of slots in the inventory
    @Override
    public int getSizeInventory() {
        return itemStacks.length;
    }

    // returns true if all of the slots in the inventory are empty
    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : itemStacks) {
            if (!itemstack.isEmpty()) { // isEmpty()
                return false;
            }
        }

        return true;
    }

    // overwrites the stack in the given slotIndex with the given stack
    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemstack) {
        itemStacks[slotIndex] = itemstack;
        if (!itemstack.isEmpty() && itemstack.getCount() > getInventoryStackLimit()) { // isEmpty(); getStackSize()
            itemstack.setCount(getInventoryStackLimit()); // setStackSize()
        }
        markDirty();
    }

    // set all slots to empty
    @Override
    public void clear() {
        Arrays.fill(itemStacks, ItemStack.EMPTY); // EMPTY_ITEM
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    // -----------------------------------------------------------------------------------------------------------
    // The following methods are not needed for this example but are part of
    // IInventory so they must be implemented

    // Unused unless your container specifically uses it.
    // Return true if the given stack is allowed to go in the given slot
    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemstack) {
        return true;
    }

    /**
     * This method removes the entire contents of the given slot and returns it.
     * Used by containers such as crafting tables which return any items in their
     * slots when you close the GUI
     *
     * @param slotIndex
     * @return
     */
    @Override
    public ItemStack removeStackFromSlot(int slotIndex) {

        ItemStack itemStack = getStackInSlot(slotIndex);
        if (!itemStack.isEmpty())
            setInventorySlotContents(slotIndex, ItemStack.EMPTY); // isEmpty(); EMPTY_ITEM
        return itemStack;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        if (this.world.getTileEntity(this.pos) != this)
            return false;
        final double X_CENTRE_OFFSET = 0.5;
        final double Y_CENTRE_OFFSET = 0.5;
        final double Z_CENTRE_OFFSET = 0.5;
        final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
        return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET, pos
                .getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
    }

    static public boolean isItemValidForFuelSlot(ItemStack itemStack) {
        return true;
    }

    static public boolean isItemValidForInputSlot(ItemStack itemStack) {
        return true;
    }

    static public boolean isItemValidForOutputSlot(ItemStack itemStack) {
        return false;
    }

    @Override
    public NBTTagCompound write(NBTTagCompound parentNBTTagCompound) {
        super.write(parentNBTTagCompound); // The super call is required to save and load the tiles location

        NBTTagList dataForAllSlots = new NBTTagList();
        for (int i = 0; i < this.itemStacks.length; ++i) {
            if (!this.itemStacks[i].isEmpty()) { // isEmpty()
                NBTTagCompound dataForThisSlot = new NBTTagCompound();
                dataForThisSlot.setByte("Slot", (byte) i);
                this.itemStacks[i].write(dataForThisSlot);
                dataForAllSlots.add(dataForThisSlot);
            }
        }
        // the array of hashmaps is then inserted into the instance hashmap for the
        // container
        parentNBTTagCompound.setTag("Items", dataForAllSlots);

        // Save everything else
        parentNBTTagCompound.setShort("CookTime", cookTime);

        parentNBTTagCompound.setInt("fuel", this.FuelRemaining);
        return parentNBTTagCompound;
    }

    // This is where you load the data that you saved in write
    @Override
    public void read(NBTTagCompound nbtTagCompound) {
        super.read(nbtTagCompound); // The super call is required to save and load the tiles location
        final byte NBT_TYPE_COMPOUND = 10; // See NBTBase.createNewByType() for a listing
        NBTTagList dataForAllSlots = nbtTagCompound.getList("Items", NBT_TYPE_COMPOUND);

        Arrays.fill(itemStacks, ItemStack.EMPTY); // set all slots to empty EMPTY_ITEM
        for (int i = 0; i < dataForAllSlots.size(); ++i) {
            NBTTagCompound dataForOneSlot = dataForAllSlots.getCompound(i);
            byte slotNumber = dataForOneSlot.getByte("Slot");
            if (slotNumber >= 0 && slotNumber < this.itemStacks.length) {
                this.itemStacks[slotNumber] = ItemStack.read(dataForOneSlot);
            }
        }

        // Load everything else. Trim the arrays (or pad with 0) to make sure they have
        // the correct number of elements
        cookTime = nbtTagCompound.getShort("CookTime");

        this.FuelRemaining = nbtTagCompound.getInt("fuel");
    }

    //	// When the world loads from disk, the server needs to send the TileEntity information to the client
    //	//  it uses getUpdatePacket(), getUpdateTag(), onDataPacket(), and handleUpdateTag() to do this
    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound updateTagDescribingTileEntityState = getUpdateTag();
        final int METADATA = 0;
        return new SPacketUpdateTileEntity(this.pos, METADATA, updateTagDescribingTileEntityState);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        NBTTagCompound updateTagDescribingTileEntityState = pkt.getNbtCompound();
        handleUpdateTag(updateTagDescribingTileEntityState);
    }

    /*
     * Creates a tag containing the TileEntity information, used by vanilla to
     * transmit from server to client Warning - although our getUpdatePacket() uses
     * this method, vanilla also calls it directly, so don't remove it.
     */
    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        write(nbtTagCompound);
        return nbtTagCompound;
    }

    /*
     * Populates this TileEntity with information from the tag, used by vanilla to
     * transmit from server to client Warning - although our onDataPacket() uses
     * this method, vanilla also calls it directly, so don't remove it.
     */
    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        this.read(tag);
    }
    // ------------------------

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getName() {
        return new TextComponentTranslation(this.getGuiID());
    }

}
