package com.robertx22.blocks.gear_factory_station;

import com.robertx22.blocks.bases.BaseTile;
import com.robertx22.items.ores.ItemOre;
import com.robertx22.loot.blueprints.GearBlueprint;
import com.robertx22.loot.blueprints.SpellBlueprint;
import com.robertx22.loot.create.GearGen;
import com.robertx22.loot.create.SpellItemGen;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.HashMap;

public class TileGearFactory extends BaseTile {

    @Override
    public boolean isAutomatable() {

        return false;
    }

    @Override
    public boolean isItemValidInput(ItemStack stack) {
        return GetFuelGain(stack) > 0;
    }

    public static HashMap<Item, Integer> MaterialValues = new HashMap<Item, Integer>() {
        {
            {
                put(Items.DIAMOND, 500);
                put(Items.GOLD_INGOT, 250);
                put(Items.IRON_INGOT, 50);
                put(Items.EMERALD, 400);
                put(Items.REDSTONE, 4);

                put(ItemOre.ItemOres.get(0), 50);
                put(ItemOre.ItemOres.get(1), 100);
                put(ItemOre.ItemOres.get(2), 150);
                put(ItemOre.ItemOres.get(3), 250);
                put(ItemOre.ItemOres.get(4), 500);
                put(ItemOre.ItemOres.get(5), 1000);

            }
        }
    };

    private int points = 0;
    private static final int pointsNeeded = 2500;
    private static final int spellChance = 15;
    private static final int maxFuel = 25000;

    public static int GetFuelGain(ItemStack stack) {
        Item item = stack.getItem();

        ItemOre.ItemOres.get(0);

        return MaterialValues.getOrDefault(item, 0);

    }

    public ItemStack getSmeltingResultForItem(ItemStack stack) {
        Item item = stack.getItem();

        if (points > pointsNeeded) {

            GearItemData gear = Gear.Load(stack);

            if (gear != null) {

                if (RandomUtils.roll(spellChance)) {
                    SpellBlueprint spellprint = new SpellBlueprint(gear.level);
                    spellprint.LevelRange = false;
                    return SpellItemGen.Create(spellprint);
                } else {
                    GearBlueprint print = new GearBlueprint(gear.level);
                    print.LevelRange = false;
                    if (RandomUtils.roll(50)) {
                        print.SetSpecificType(gear.gearTypeName);
                    }

                    return GearGen.CreateStack(print);
                }

            }

        }

        return ItemStack.EMPTY;

    }
    // IMPORTANT STUFF ABOVE

    // Create and initialize the itemStacks variable that will store store the
    // itemStacks
    public static final int FUEL_SLOTS_COUNT = 1;
    public static final int INPUT_SLOTS_COUNT = 5;
    public static final int OUTPUT_SLOTS_COUNT = 5;
    public static final int TOTAL_SLOTS_COUNT = FUEL_SLOTS_COUNT + INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT + 1;

    public static final int FIRST_FUEL_SLOT = 0;
    public static final int FIRST_INPUT_SLOT = FIRST_FUEL_SLOT + FUEL_SLOTS_COUNT;
    public static final int FIRST_OUTPUT_SLOT = FIRST_INPUT_SLOT + INPUT_SLOTS_COUNT;
    public static final int FIRST_CAPACITOR_SLOT = FIRST_OUTPUT_SLOT + OUTPUT_SLOTS_COUNT;

    private static final short COOK_TIME_FOR_COMPLETION = 200; // vanilla value is 200 = 10 seconds

    public TileGearFactory() {
        super(StartupGearFactory.GEAR_FACTORY);
        itemStacks = new ItemStack[TOTAL_SLOTS_COUNT];
        clear();
    }

    /**
     * Returns the amount of fuel remaining on the currently burning item in the
     * given fuel slot.
     *
     * @return fraction remaining, between 0 - 1
     * @fuelSlot the number of the fuel slot (0..3)
     */
    public double fractionOfFuelRemaining(int fuelSlot) {
        if (this.points <= 0)
            return 0;
        double fraction = points / (double) maxFuel;
        return MathHelper.clamp(fraction, 0.0, 1.0);
    }

    /**
     * return the remaining burn time of the fuel in the given slot
     *
     * @param fuelSlot the number of the fuel slot (0..3)
     * @return seconds remaining
     */
    public int secondsOfFuelRemaining(int fuelSlot) {
        if (points <= 0)
            return 0;
        return points; // 20 ticks per second
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

                    cookTime += 5;

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
     * for each fuel slot: decreases the burn time, checks if burnTimeRemaining = 0
     * and tries to consume a new piece of fuel if one is available
     *
     * @return the number of fuel slots which are burning
     */
    private int burnFuel() {
        int burningCount = 0;
        boolean inventoryChanged = false;
        // Iterate over all the fuel slots
        for (int i = 0; i < FUEL_SLOTS_COUNT; i++) {
            int fuelSlotNumber = i + FIRST_FUEL_SLOT;

            if (this.points < this.maxFuel) {
                if (!itemStacks[fuelSlotNumber].isEmpty()) { // isEmpty()
                    // If the stack in this slot is not null and is fuel, set burnTimeRemaining &
                    // burnTimeInitialValue to the
                    // item's burn time and decrease the stack size

                    int fuel = GetFuelGain(itemStacks[fuelSlotNumber]);

                    if (fuel > 0) {
                        this.points += fuel;

                    } else {
                        return 0;
                    }

                    itemStacks[fuelSlotNumber].shrink(1); // decreaseStackSize()
                    ++burningCount;
                    inventoryChanged = true;
                    // If the stack size now equals 0 set the slot contents to the items container
                    // item. This is for fuel
                    // items such as lava buckets so that the bucket is not consumed. If the item
                    // dose not have
                    // a container item getContainerItem returns null which sets the slot contents
                    // to null
                    if (itemStacks[fuelSlotNumber].getCount() == 0) { // getStackSize()
                        itemStacks[fuelSlotNumber] = itemStacks[fuelSlotNumber].getItem()
                                .getContainerItem(itemStacks[fuelSlotNumber]);
                    }
                }
            }
        }
        if (inventoryChanged)
            markDirty();
        return burningCount;
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
        ItemStack result = ItemStack.EMPTY; // EMPTY_ITEM

        // finds the first input slot which is smeltable and whose result fits into an
        // output slot (stacking if possible)
        for (int inputSlot = FIRST_INPUT_SLOT; inputSlot < FIRST_INPUT_SLOT + INPUT_SLOTS_COUNT; inputSlot++) {
            if (!itemStacks[inputSlot].isEmpty()) { // isEmpty()

                if (pointsNeeded < this.points) {
                    result = getSmeltingResultForItem(itemStacks[inputSlot]);

                } else {
                    result = ItemStack.EMPTY;
                }

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

                        if (outputStack.getItem() == result.getItem() && ItemStack.areItemStackTagsEqual(outputStack, result)) {
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
        //itemStacks[this.FIRST_CAPACITOR_SLOT] = ItemStack.EMPTY; WTF?
        if (itemStacks[firstSuitableInputSlot].getCount() <= 0) {
            itemStacks[firstSuitableInputSlot] = ItemStack.EMPTY; // getStackSize(), EmptyItem
        }
        if (itemStacks[firstSuitableOutputSlot].isEmpty()) { // isEmpty()
            itemStacks[firstSuitableOutputSlot] = result.copy(); // Use deep .copy() to avoid altering the recipe
        } else {
            int newStackSize = itemStacks[firstSuitableOutputSlot].getCount() + result.getCount();
            itemStacks[firstSuitableOutputSlot].setCount(newStackSize); // setStackSize(), getStackSize()
        }

        points -= pointsNeeded;

        markDirty();
        return true;

    }

    private static final byte COOK_FIELD_ID = 0;
    private static final byte FIRST_BURN_TIME_REMAINING_FIELD_ID = 1;
    private static final byte FIRST_BURN_TIME_INITIAL_FIELD_ID = FIRST_BURN_TIME_REMAINING_FIELD_ID + (byte) FUEL_SLOTS_COUNT;
    private static final byte NUMBER_OF_FIELDS = FIRST_BURN_TIME_INITIAL_FIELD_ID + (byte) FUEL_SLOTS_COUNT;

    @Override
    public int getField(int id) {
        if (id == COOK_FIELD_ID)
            return cookTime;
        if (id >= FIRST_BURN_TIME_REMAINING_FIELD_ID && id < FIRST_BURN_TIME_REMAINING_FIELD_ID + FUEL_SLOTS_COUNT) {
            return this.points;
        }

        // System.err.println("Invalid field ID in TileInventorySmelting.getField:" +
        // id);
        return 0;
    }

    @Override
    public void setField(int id, int value) {
        if (id == COOK_FIELD_ID) {
            cookTime = (short) value;
        } else if (id >= FIRST_BURN_TIME_REMAINING_FIELD_ID && id < FIRST_BURN_TIME_REMAINING_FIELD_ID + FUEL_SLOTS_COUNT) {
            this.points = value;
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
    public ITextComponent getCustomName() {
        return new TextComponentString("Factory");
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory,
                                     EntityPlayer playerIn) {

        return new ContainerGearFactory(playerInventory, this);
    }

    @Override
    public String getGuiID() {
        return Ref.MODID + ":gear_factory_gui";
    }

}