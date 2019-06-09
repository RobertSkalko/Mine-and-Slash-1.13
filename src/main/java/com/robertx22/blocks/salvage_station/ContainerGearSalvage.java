package com.robertx22.blocks.salvage_station;

import com.robertx22.blocks.bases.BaseTileContainer;
import com.robertx22.mmorpg.registers.ContainerTypeRegisters;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGearSalvage extends BaseTileContainer {

    // Stores the tile entity instance for later use
    private TileGearSalvage tileGearSalvage;

    // These store cache values, used by the server to only update the client side
    // tile entity when values have changed
    private int[] cachedFields;

    private final int HOTBAR_SLOT_COUNT = 9;
    private final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    public final int INPUT_SLOTS_COUNT = 5;
    public final int OUTPUT_SLOTS_COUNT = 5;
    public final int SALVAGE_SLOTS_COUNT = INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT + 1;

    // slot index is the unique index for all slots in this container i.e. 0 - 35
    // for invPlayer then 36 - 49 for tileInventoryFurnace
    private final int VANILLA_FIRST_SLOT_INDEX = 0;
    private final int FIRST_INPUT_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private final int FIRST_OUTPUT_SLOT_INDEX = FIRST_INPUT_SLOT_INDEX + INPUT_SLOTS_COUNT;
    private final int FIRST_CAPACITOR_SLOT_INDEX = FIRST_OUTPUT_SLOT_INDEX + OUTPUT_SLOTS_COUNT;

    // slot number is the slot number within each component; i.e. invPlayer slots 0
    // - 35, and tileInventoryFurnace slots 0 - 14
    private final int FIRST_INPUT_SLOT_NUMBER = 0;
    private final int FIRST_OUTPUT_SLOT_NUMBER = FIRST_INPUT_SLOT_NUMBER + INPUT_SLOTS_COUNT;
    private final int FIRST_CAPACITOR_SLOT_NUMBER = FIRST_OUTPUT_SLOT_NUMBER + OUTPUT_SLOTS_COUNT;

    public ContainerGearSalvage(int i, PlayerInventory playerInventory) {
        super(ContainerTypeRegisters.GEAR_SALVAGE, i);
    }

    public ContainerGearSalvage(int num, PlayerInventory invPlayer,
                                TileGearSalvage tile) {
        super(ContainerTypeRegisters.GEAR_SALVAGE, num);

        this.tileGearSalvage = tile;

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

        final int INPUT_SLOTS_XPOS = 26;
        final int INPUT_SLOTS_YPOS = 24;
        // Add the tile input slots
        for (int y = 0; y < INPUT_SLOTS_COUNT; y++) {
            int slotNumber = y + FIRST_INPUT_SLOT_NUMBER;
            addSlot(new SlotSmeltableInput(tileGearSalvage, slotNumber, INPUT_SLOTS_XPOS, INPUT_SLOTS_YPOS + SLOT_Y_SPACING * y));
        }

        final int OUTPUT_SLOTS_XPOS = 134;
        final int OUTPUT_SLOTS_YPOS = 24;
        // Add the tile output slots
        for (int y = 0; y < OUTPUT_SLOTS_COUNT; y++) {
            int slotNumber = y + FIRST_OUTPUT_SLOT_NUMBER;
            addSlot(new SlotOutput(tileGearSalvage, slotNumber, OUTPUT_SLOTS_XPOS, OUTPUT_SLOTS_YPOS + SLOT_Y_SPACING * y));
        }

        final int CAPACITOR_SLOTS_XPOS = 80; // 53; // TODO
        final int CAPACITOR_SLOTS_YPOS = 24;
        // Add the tile capacitor slot
        for (int x = 0; x < 1; x++) {
            int slotNumber = x + FIRST_CAPACITOR_SLOT_NUMBER;
            addSlot(new Slot(tileGearSalvage, slotNumber, CAPACITOR_SLOTS_XPOS + SLOT_X_SPACING * x, CAPACITOR_SLOTS_YPOS));
        }

    }

    // Checks each tick to make sure the player is still able to access the
    // inventory and if not closes the gui
    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return tileGearSalvage.isUsableByPlayer(player);
    }

    private boolean IsCustomContainer(int index) {

        return index > VANILLA_FIRST_SLOT_INDEX + this.VANILLA_SLOT_COUNT;
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
            // if (!TileInventorySalvage.getSmeltingResultForItem(sourceStack).isEmpty()) {
            // // isEmptyItem
            if (!mergeItemStack(sourceStack, FIRST_INPUT_SLOT_INDEX, FIRST_INPUT_SLOT_INDEX + INPUT_SLOTS_COUNT, false)) {
                return ItemStack.EMPTY; // EMPTY_ITEM;
                // }
            } else {
                return ItemStack.EMPTY; // this is the thing that gave me problems.. without this it crashes without an
            } // error message

        } else if (sourceSlotIndex >= FIRST_INPUT_SLOT_INDEX && sourceSlotIndex < FIRST_INPUT_SLOT_INDEX + SALVAGE_SLOTS_COUNT) {
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
            return TileGearSalvage.isItemValidForInputSlot(stack);
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
            return TileGearSalvage.isItemValidForOutputSlot(stack);
        }
    }

}