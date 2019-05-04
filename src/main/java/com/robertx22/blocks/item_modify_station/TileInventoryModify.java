package com.robertx22.blocks.item_modify_station;

import com.robertx22.blocks.bases.BaseTile;
import com.robertx22.items.currency.ICurrencyItemEffect;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.datasaving.Gear;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

public class TileInventoryModify extends BaseTile {

    @Override
    public boolean isAutomatable() {
        return false;
    }

    @Override
    public boolean isItemValidInput(ItemStack stack) {
        return true;
    }

    public ItemStack getSmeltingResultForItem(ItemStack stack) {

        ItemStack gearStack = this.GearSlot();
        ItemStack craftStack = this.CraftItemSlot();

        if (gearStack == null || gearStack.isEmpty() || craftStack == null || craftStack.isEmpty()) {
            return ItemStack.EMPTY;
        }

        GearItemData gear = Gear.Load(stack);

        if (gear != null && craftStack.getItem() instanceof ICurrencyItemEffect) {

            ICurrencyItemEffect effect = (ICurrencyItemEffect) craftStack.getItem();

            if (effect.canItemBeModified(gearStack, craftStack)) {
                ItemStack copy = gearStack.copy();
                copy = effect.ModifyItem(copy, craftStack);
                return copy;
            } else {
                return ItemStack.EMPTY;
            }

        }

        return ItemStack.EMPTY;
    }

    public ItemStack GearSlot() {
        return itemStacks[FIRST_INPUT_SLOT];
    }

    public ItemStack CraftItemSlot() {
        return itemStacks[FIRST_INPUT_SLOT + 1];
    }

    // IMPORTANT STUFF ABOVE

    // Create and initialize the itemStacks variable that will store store the
    // itemStacks
    public static final int INPUT_SLOTS_COUNT = 2;
    public static final int OUTPUT_SLOTS_COUNT = 1;
    public static final int TOTAL_SLOTS_COUNT = INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT;

    public static final int FIRST_INPUT_SLOT = 0;
    public static final int FIRST_OUTPUT_SLOT = FIRST_INPUT_SLOT + INPUT_SLOTS_COUNT;

    private static final short COOK_TIME_FOR_COMPLETION = 200; // vanilla value is 200 = 10 seconds

    public TileInventoryModify() {
        super(StartupModify.GEAR_MODIFY);
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

    @Override
    public void tick() {
        if (!this.world.isRemote) {

            ticks++;
            if (ticks > 20) {
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
        ItemStack result = ItemStack.EMPTY;

        // finds the first input slot which is smeltable and whose result fits into an
        // output slot (stacking if possible)
        for (int inputSlot = FIRST_INPUT_SLOT; inputSlot < FIRST_INPUT_SLOT + INPUT_SLOTS_COUNT; inputSlot++) {
            if (!itemStacks[inputSlot].isEmpty()) { // isEmpty()

                result = getSmeltingResultForItem(itemStacks[inputSlot]);

                if (!result.isEmpty()) { // isEmpty()
                    // find the first suitable output slot- either empty, or with identical item
                    // that has enough space
                    for (int outputSlot = FIRST_OUTPUT_SLOT; outputSlot < FIRST_OUTPUT_SLOT + OUTPUT_SLOTS_COUNT; outputSlot++) {
                        ItemStack outputStack = itemStacks[outputSlot];
                        if (outputStack.isEmpty()) { // isEmpty()
                            firstSuitableInputSlot = inputSlot;
                            firstSuitableOutputSlot = outputSlot;
                            break;
                        }

                        if (outputStack.getItem() == result.getItem()

                                && ItemStack.areItemStackTagsEqual(outputStack, result)) {
                            int combinedSize = itemStacks[outputSlot].getCount() + result.getCount(); // getStackSize()
                            if (combinedSize <= getInventoryStackLimit() && combinedSize <= itemStacks[outputSlot]
                                    .getMaxStackSize()) {
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

        this.CraftItemSlot().setCount(this.CraftItemSlot().getCount() - 1);

        markDirty();
        return true;
    }

    // will add a key for this container to the lang file so we can name it in the
    // GUI

    @Override
    public boolean hasCustomName() {
        return false;
    }

    // standard code to look up what the human-readable name is

    private static final byte COOK_FIELD_ID = 0;
    private static final byte NUMBER_OF_FIELDS = 1;

    @Override
    public int getField(int id) {
        if (id == COOK_FIELD_ID)
            return cookTime;

        // System.err.println("Invalid field GEAR_FACTORY_ID in TileInventorySmelting.getField:" +
        // id);
        return 0;
    }

    @Override
    public void setField(int id, int value) {
        if (id == COOK_FIELD_ID) {
            cookTime = (short) value;
        } else {
            // System.err.println("Invalid field GEAR_FACTORY_ID in TileInventorySmelting.setField:" +
            // id);
        }
    }

    @Override
    public int getFieldCount() {
        return NUMBER_OF_FIELDS;
    }

    @Override
    public ITextComponent getName() {
        return CLOC.blank("block.mmorpg.modify_station");
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory,
                                     EntityPlayer playerIn) {
        return new ContainerInventoryModify(playerInventory, this);
    }

    @Override
    public String getGuiID() {
        return StartupModify.ID;
    }

}