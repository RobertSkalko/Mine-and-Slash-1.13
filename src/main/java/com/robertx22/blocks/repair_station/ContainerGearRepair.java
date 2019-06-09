package com.robertx22.blocks.repair_station;

import com.robertx22.blocks.bases.BaseTileContainer;
import com.robertx22.mmorpg.registers.ContainerTypeRegisters;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;

/**
 * User: brandon3055 Date: 06/01/2015
 * <p>
 * ContainerSmelting is used to link the client side gui to the server side
 * inventory and it is where you add the slots holding items. It is also used to
 * send server side dataInstance such as progress bars to the client for use in guis
 */
public class ContainerGearRepair extends BaseTileContainer {

    // Stores the tile entity instance for later use
    private TileGearRepair tileGearRepair;

    // These store cache values, used by the server to only update the client side
    // tile entity when values have changed
    private int[] cachedFields;

    // must assign a slot index to each of the slots used by the GUI.
    // For this container, we can see the furnace fuel, input, and output slots as
    // well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container using addSlot(), it
    // automatically increases the slotIndex, which means
    // 0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 -
    // 8)
    // 9 - 35 = player inventory slots (which map to the InventoryPlayer slot
    // numbers 9 - 35)
    // 36 - 39 = fuel slots (tileEntity 0 - 3)
    // 40 - 44 = input slots (tileEntity 4 - 8)
    // 45 - 49 = output slots (tileEntity 9 - 13)

    private final int HOTBAR_SLOT_COUNT = 9;
    private final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    public final int FUEL_SLOTS_COUNT = TileGearRepair.FUEL_SLOTS_COUNT;
    public final int INPUT_SLOTS_COUNT = 5;
    public final int OUTPUT_SLOTS_COUNT = 5;
    public final int FURNACE_SLOTS_COUNT = FUEL_SLOTS_COUNT + INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT + 1;

    // slot index is the unique index for all slots in this container i.e. 0 - 35
    // for invPlayer then 36 - 49 for tileInventoryFurnace
    private final int VANILLA_FIRST_SLOT_INDEX = 0;
    private final int FIRST_FUEL_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private final int FIRST_INPUT_SLOT_INDEX = FIRST_FUEL_SLOT_INDEX + FUEL_SLOTS_COUNT;
    private final int FIRST_OUTPUT_SLOT_INDEX = FIRST_INPUT_SLOT_INDEX + INPUT_SLOTS_COUNT;
    private final int FIRST_CAPACITOR_SLOT_INDEX = FIRST_OUTPUT_SLOT_INDEX + OUTPUT_SLOTS_COUNT;

    // slot number is the slot number within each component; i.e. invPlayer slots 0
    // - 35, and tileInventoryFurnace slots 0 - 14
    private final int FIRST_FUEL_SLOT_NUMBER = 0;
    private final int FIRST_INPUT_SLOT_NUMBER = FIRST_FUEL_SLOT_NUMBER + FUEL_SLOTS_COUNT;
    private final int FIRST_OUTPUT_SLOT_NUMBER = FIRST_INPUT_SLOT_NUMBER + INPUT_SLOTS_COUNT;
    private final int FIRST_CAPACITOR_SLOT_NUMBER = FIRST_OUTPUT_SLOT_NUMBER + OUTPUT_SLOTS_COUNT;

    public ContainerGearRepair(int i, PlayerInventory playerInventory) {
        super(ContainerTypeRegisters.GEAR_REPAIR, i);
    }

    public ContainerGearRepair(int num, PlayerInventory invPlayer, TileGearRepair tile) {
        super(ContainerTypeRegisters.GEAR_REPAIR, num);

        this.tileGearRepair = tile;

        final int SLOT_X_SPACING = 18;
        final int SLOT_Y_SPACING = 18;
        final int HOTBAR_XPOS = 8;
        final int HOTBAR_YPOS = 183;
        // Add the players hotbar to the gui - the [xpos, ypos] location of each item
        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
            int slotNumber = x;
            addSlot(new Slot(invPlayer, slotNumber, HOTBAR_XPOS + SLOT_X_SPACING * x, HOTBAR_YPOS));
        }

        final int PLAYER_INVENTORY_XPOS = 8;
        final int PLAYER_INVENTORY_YPOS = 125;
        // Add the rest of the players inventory to the gui
        for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
            for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
                int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
                int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
                int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
                addSlot(new Slot(invPlayer, slotNumber, xpos, ypos));
            }
        }

        final int FUEL_SLOTS_XPOS = 80; // 53; // TODO
        final int FUEL_SLOTS_YPOS = 96;
        // Add the tile fuel slots
        for (int x = 0; x < FUEL_SLOTS_COUNT; x++) {
            int slotNumber = x + FIRST_FUEL_SLOT_NUMBER;
            addSlot(new SlotFuel(tile, slotNumber, FUEL_SLOTS_XPOS + SLOT_X_SPACING * x, FUEL_SLOTS_YPOS));
        }

        final int INPUT_SLOTS_XPOS = 26;
        final int INPUT_SLOTS_YPOS = 24;
        // Add the tile input slots
        for (int y = 0; y < INPUT_SLOTS_COUNT; y++) {
            int slotNumber = y + FIRST_INPUT_SLOT_NUMBER;
            addSlot(new SlotSmeltableInput(tile, slotNumber, INPUT_SLOTS_XPOS, INPUT_SLOTS_YPOS + SLOT_Y_SPACING * y));
        }

        final int OUTPUT_SLOTS_XPOS = 134;
        final int OUTPUT_SLOTS_YPOS = 24;
        // Add the tile output slots
        for (int y = 0; y < OUTPUT_SLOTS_COUNT; y++) {
            int slotNumber = y + FIRST_OUTPUT_SLOT_NUMBER;
            addSlot(new SlotOutput(tile, slotNumber, OUTPUT_SLOTS_XPOS, OUTPUT_SLOTS_YPOS + SLOT_Y_SPACING * y));
        }

        final int CAPACITOR_SLOTS_XPOS = 80; // 53; // TODO
        final int CAPACITOR_SLOTS_YPOS = 24;
        // Add the tile capacitor slot
        for (int x = 0; x < 1; x++) {
            int slotNumber = x + FIRST_CAPACITOR_SLOT_NUMBER;
            addSlot(new Slot(tile, slotNumber, CAPACITOR_SLOTS_XPOS + SLOT_X_SPACING * x, CAPACITOR_SLOTS_YPOS));
        }

    }

    // Checks each tick to make sure the player is still able to access the
    // inventory and if not closes the gui
    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return tileGearRepair.isUsableByPlayer(player);
    }

    // shift click logic
    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int sourceSlotIndex) {
        Slot sourceSlot = (Slot) inventorySlots.get(sourceSlotIndex);
        if (sourceSlot == null || !sourceSlot.getHasStack())
            return ItemStack.EMPTY; // EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getStack();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (sourceSlotIndex >= VANILLA_FIRST_SLOT_INDEX && sourceSlotIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into one of the furnace
            // slots
            // If the stack is smeltable try to merge merge the stack into the input slots
            if (!new TileGearRepair().getSmeltingResultForItem(sourceStack)
                    .isEmpty()) { // isEmptyItem
                if (!mergeItemStack(sourceStack, FIRST_INPUT_SLOT_INDEX, FIRST_INPUT_SLOT_INDEX + INPUT_SLOTS_COUNT, false)) {
                    return ItemStack.EMPTY; // EMPTY_ITEM;
                }
            } else {
                return ItemStack.EMPTY; // EMPTY_ITEM;
            }
        } else if (sourceSlotIndex >= FIRST_FUEL_SLOT_INDEX && sourceSlotIndex < FIRST_FUEL_SLOT_INDEX + FURNACE_SLOTS_COUNT) {
            // This is a furnace slot so merge the stack into the players inventory: try the
            // hotbar first and then the main inventory
            // because the main inventory slots are immediately after the hotbar slots, we
            // can just merge with a single call
            if (!mergeItemStack(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY; // EMPTY_ITEM;
            }
        } else {
            System.err.print("Invalid slotIndex:" + sourceSlotIndex);
            return ItemStack.EMPTY; // EMPTY_ITEM;
        }

        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) { // getStackSize()
            sourceSlot.putStack(ItemStack.EMPTY); // Empty Item
        } else {
            sourceSlot.onSlotChanged();
        }

        sourceSlot.onTake(player, sourceStack); // onPickupFromSlot()
        return copyOfSourceStack;
    }

    // SlotFuel is a slot for fuel items
    public class SlotFuel extends Slot {
        public SlotFuel(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        // if this function returns false, the player won't be able to insert the given
        // item into this slot
        @Override
        public boolean isItemValid(ItemStack stack) {
            return TileGearRepair.isItemValidForFuelSlot(stack);
        }
    }

    // SlotSmeltableInput is a slot for input items
    public class SlotSmeltableInput extends Slot {
        public SlotSmeltableInput(IInventory inventoryIn, int index, int xPosition,
                                  int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        // if this function returns false, the player won't be able to insert the given
        // item into this slot
        @Override
        public boolean isItemValid(ItemStack stack) {
            return TileGearRepair.isItemValidForInputSlot(stack);
        }
    }

    // SlotOutput is a slot that will not accept any items
    public class SlotOutput extends Slot {
        public SlotOutput(IInventory inventoryIn, int index, int xPosition,
                          int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        // if this function returns false, the player won't be able to insert the given
        // item into this slot
        @Override
        public boolean isItemValid(ItemStack stack) {
            return TileGearRepair.isItemValidForOutputSlot(stack);
        }
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Repair");
    }

    @Nullable
    @Override
    public Container createMenu(int num, PlayerInventory inv, PlayerEntity player) {
        return ContainerTypeRegisters.GEAR_REPAIR.func_221506_a(num, inv);
    }

}