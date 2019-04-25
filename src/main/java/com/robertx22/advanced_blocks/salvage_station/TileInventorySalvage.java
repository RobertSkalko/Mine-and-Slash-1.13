package com.robertx22.advanced_blocks.salvage_station;

import java.util.Arrays;

import javax.annotation.Nullable;

import com.robertx22.advanced_blocks.BaseTile;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Map;
import com.robertx22.uncommon.datasaving.Spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class TileInventorySalvage extends BaseTile {

    @Override
    public int[] inputSlots() {
	int[] ints = new int[INPUT_SLOTS_COUNT];
	for (int i = 0; i < INPUT_SLOTS_COUNT; i++) {
	    ints[i] = i;
	}

	return ints;
    }

    @Override
    public boolean isAutomatable() {
	return true;
    }

    public ItemStack getSmeltingResultForItem(ItemStack st) {

	GearItemData gear = Gear.Load(st);
	if (gear != null) {
	    return gear.getSalvageResult(0);
	}

	SpellItemData spell = Spell.Load(st);
	if (spell != null) {
	    return spell.getSalvageResult(0);
	}

	MapItemData map = Map.Load(st);
	if (map != null) {
	    return map.getSalvageResult(0);
	}

	return ItemStack.EMPTY;

    }

    // IMPORTANT STUFF ABOVE

    // Create and initialize the itemStacks variable that will store store the
    // itemStacks
    public static final int INPUT_SLOTS_COUNT = 5;
    public static final int OUTPUT_SLOTS_COUNT = 5;
    public static final int TOTAL_SLOTS_COUNT = INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT + 1;

    public static final int FIRST_INPUT_SLOT = 0;
    public static final int FIRST_OUTPUT_SLOT = FIRST_INPUT_SLOT + INPUT_SLOTS_COUNT;
    public static final int FIRST_CAPACITOR_SLOT = FIRST_OUTPUT_SLOT + OUTPUT_SLOTS_COUNT;

    /** The number of ticks the current item has been cooking */
    private short cookTime;
    /** The number of ticks required to cook an item */
    private static final short COOK_TIME_FOR_COMPLETION = 200; // vanilla value is 200 = 10 seconds

    public TileInventorySalvage() {
	super(StartupSalvage.GEAR_SALVAGE);
	itemStacks = new ItemStack[TOTAL_SLOTS_COUNT];
	clear();
    }

    /**
     * Returns the amount of cook time completed on the currently cooking item.
     * 
     * @return fraction remaining, between 0 - 1
     */
    public double fractionOfCookTimeComplete() {
	double fraction = cookTime / (double) COOK_TIME_FOR_COMPLETION;
	return MathHelper.clamp(fraction, 0.0, 1.0);
    }

    int ticks = 0;

    @Override
    public void tick() {

	if (!this.world.isRemote) {
	    ticks++;
	    if (ticks > 10) {
		ticks = 0;
		if (canSmelt()) {

		    cookTime += 20;

		    if (cookTime < 0)
			cookTime = 0;

		    // If cookTime has reached maxCookTime smelt the item and reset cookTime
		    if (cookTime >= COOK_TIME_FOR_COMPLETION) {
			smeltItem();
			cookTime = 0;
		    }
		} else {
		    cookTime = 0;
		}
	    }
	}

    }

    /**
     * Check if any of the input items are smeltable and there is sufficient space
     * in the output slots
     * 
     * @return true if smelting is possible
     */
    private boolean canSmelt() {
	return smeltItem(false);
    }

    /**
     * Smelt an input item into an output slot, if possible
     */
    private void smeltItem() {
	smeltItem(true);
    }

    ItemStack result = ItemStack.EMPTY;

    /**
     * checks that there is an item to be smelted in one of the input slots and that
     * there is room for the result in the output slots If desired, performs the
     * smelt
     * 
     * @param performSmelt if true, perform the smelt. if false, check whether
     *                     smelting is possible, but don't change the inventory
     * @return false if no items can be smelted, true otherwise
     */
    private boolean smeltItem(boolean performSmelt) {
	Integer firstSuitableInputSlot = null;
	Integer firstSuitableOutputSlot = null;

	// TODO

	/*
	 * if (!itemStacks[FIRST_CAPACITOR_SLOT].isEmpty()) {
	 * 
	 * Item item = itemStacks[FIRST_CAPACITOR_SLOT].getItem();
	 * 
	 * if (item instanceof ItemCapacitor) { fuelMulti = ((ItemCapacitor)
	 * item).GetFuelMultiplier(); // System.out.println("it works!"); }
	 * 
	 * }
	 */

	// finds the first input slot which is smeltable and whose result fits into an
	// output slot (stacking if possible)
	for (int inputSlot = FIRST_INPUT_SLOT; inputSlot < FIRST_INPUT_SLOT + INPUT_SLOTS_COUNT; inputSlot++) {
	    if (!itemStacks[inputSlot].isEmpty()) { // isEmpty()

		result = getSmeltingResultForItem(itemStacks[inputSlot]);

		if (!result.isEmpty()) { // isEmpty()
		    // find the first suitable output slot- either empty, or with identical item
		    // that has enough space
		    for (int outputSlot = FIRST_OUTPUT_SLOT; outputSlot < FIRST_OUTPUT_SLOT
			    + OUTPUT_SLOTS_COUNT; outputSlot++) {
			ItemStack outputStack = itemStacks[outputSlot];
			if (outputStack.isEmpty()) { // isEmpty()
			    firstSuitableInputSlot = inputSlot;
			    firstSuitableOutputSlot = outputSlot;
			    break;
			}

			if (outputStack.getItem() == result.getItem()

				&& ItemStack.areItemStackTagsEqual(outputStack, result)) {
			    int combinedSize = itemStacks[outputSlot].getCount() + result.getCount(); // getStackSize()
			    if (combinedSize <= getInventoryStackLimit()
				    && combinedSize <= itemStacks[outputSlot].getMaxStackSize()) {
				firstSuitableInputSlot = inputSlot;
				firstSuitableOutputSlot = outputSlot;
				break;
			    }
			}
		    }
		    if (firstSuitableInputSlot != null)
			break;
		}
	    }
	}

	if (firstSuitableInputSlot == null)
	    return false;
	if (!performSmelt)
	    return true;

	// alter input and output
	itemStacks[firstSuitableInputSlot].shrink(1); // decreaseStackSize()
	if (itemStacks[firstSuitableInputSlot].getCount() <= 0) {
	    itemStacks[firstSuitableInputSlot] = ItemStack.EMPTY; // getStackSize(), EmptyItem
	}
	if (itemStacks[firstSuitableOutputSlot].isEmpty()) { // isEmpty()
	    itemStacks[firstSuitableOutputSlot] = result.copy(); // Use deep .copy() to avoid altering the recipe
	    result = ItemStack.EMPTY;

	} else {
	    int newStackSize = itemStacks[firstSuitableOutputSlot].getCount() + result.getCount();
	    itemStacks[firstSuitableOutputSlot].setCount(newStackSize); // setStackSize(), getStackSize()
	}

	markDirty();
	return true;
    }

    @Override
    public NBTTagCompound write(NBTTagCompound parentNBTTagCompound) {
	super.write(parentNBTTagCompound); // The super call is required to save and load the tiles location

	NBTTagList dataForAllSlots = new NBTTagList();
	for (int i = 0; i < this.itemStacks.length; ++i) {
	    if (!this.itemStacks[i].isEmpty()) { // isEmpty()
		NBTTagCompound dataForThisSlot = new NBTTagCompound();
		dataForThisSlot.putByte("Slot", (byte) i);
		this.itemStacks[i].write(dataForThisSlot);
		dataForAllSlots.add(dataForThisSlot);
	    }
	}
	// the array of hashmaps is then inserted into the parent hashmap for the
	// container
	parentNBTTagCompound.put("Items", dataForAllSlots);

	// Save everything else
	parentNBTTagCompound.putShort("CookTime", cookTime);

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

    private static final byte COOK_FIELD_ID = 0;
    private static final byte FIRST_BURN_TIME_REMAINING_FIELD_ID = 1;
    private static final byte NUMBER_OF_FIELDS = 1;

    @Override
    public int getField(int id) {
	if (id == COOK_FIELD_ID)
	    return cookTime;

	// System.err.println("Invalid field ID in TileInventorySmelting.getField:" +
	// id);
	return 0;
    }

    @Override
    public void setField(int id, int value) {
	if (id == COOK_FIELD_ID) {
	    cookTime = (short) value;
	} else {
	    // System.err.println("Invalid field ID in TileInventorySmelting.setField:" +
	    // id);
	}
    }

    @Override
    public int getFieldCount() {
	return NUMBER_OF_FIELDS;
    }

    @Override
    public boolean isItemValidInput(ItemStack stack) {
	return this.getSmeltingResultForItem(stack).isEmpty() == false;
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
	return this.getName();
    }

    @Override
    public ITextComponent getName() {
	return new TextComponentString(CLOC.blank("block.mmorpg:salvage_station"));
    }

    @Override
    public ITextComponent getCustomName() {
	return new TextComponentString("");
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
	return new ContainerInventorySalvage(playerInventory, this);
    }

    @Override
    public String getGuiID() {
	return Ref.MODID + "gear_salvage_station_gui";
    }

}