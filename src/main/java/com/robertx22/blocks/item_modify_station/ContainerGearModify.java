package com.robertx22.blocks.item_modify_station;

import com.robertx22.blocks.bases.BaseTileContainer;
import com.robertx22.blocks.salvage_station.TileGearSalvage;
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

public class ContainerGearModify extends BaseTileContainer {

    // Stores the tile entity instance for later use
    private TileGearModify tileInventory;

    // These store cache values, used by the server to only update the client side
    // tile entity when values have changed
    private int[] cachedFields;

    private final int HOTBAR_SLOT_COUNT = 9;
    private final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    public final int INPUT_SLOTS_COUNT = 2;
    public final int OUTPUT_SLOTS_COUNT = 1;
    public final int MODIFY_SLOTS_COUNT = INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT;

    // slot index is the unique index for all slots in this container i.e. 0 - 35
    // for invPlayer then 36 - 49 for tileInventoryFurnace
    private final int VANILLA_FIRST_SLOT_INDEX = 0;
    private final int FIRST_INPUT_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private final int FIRST_OUTPUT_SLOT_INDEX = FIRST_INPUT_SLOT_INDEX + INPUT_SLOTS_COUNT;

    // slot number is the slot number within each component; i.e. invPlayer slots 0
    // - 35, and tileInventoryFurnace slots 0 - 14
    private final int FIRST_INPUT_SLOT_NUMBER = 0;
    private final int FIRST_OUTPUT_SLOT_NUMBER = FIRST_INPUT_SLOT_NUMBER + INPUT_SLOTS_COUNT;

    public ContainerGearModify(int i, PlayerInventory playerInventory) {
        super(ContainerTypeRegisters.GEAR_MODIFY, i);
    }

    public ContainerGearModify(int num, PlayerInventory invPlayer, TileGearModify tile) {
        super(ContainerTypeRegisters.GEAR_MODIFY, num);

        this.tileInventory = tileInventory;

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

        // VANILLA END

        final int INPUT_SLOTS_XPOS = 26;
        final int INPUT_SLOTS_YPOS = 24;
        // Add the tile input slots

        addSlot(new SlotSmeltableInput(tileInventory, FIRST_INPUT_SLOT_NUMBER, INPUT_SLOTS_XPOS, INPUT_SLOTS_YPOS + SLOT_Y_SPACING * 2));

        addSlot(new SlotSmeltableInput(tileInventory, FIRST_INPUT_SLOT_NUMBER + 1, 72, INPUT_SLOTS_YPOS + SLOT_Y_SPACING * 1));

        final int OUTPUT_SLOTS_XPOS = 134;
        final int OUTPUT_SLOTS_YPOS = 24;

        addSlot(new SlotOutput(tileInventory, FIRST_OUTPUT_SLOT_NUMBER, OUTPUT_SLOTS_XPOS, OUTPUT_SLOTS_YPOS + SLOT_Y_SPACING * 2));

    }

    // Checks each tick to make sure the player is still able to access the
    // inventory and if not closes the gui
    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return tileInventory.isUsableByPlayer(player);
    }

    // shift click logic
    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int sourceSlotIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Modify");
    }

    @Nullable
    @Override
    public Container createMenu(int num, PlayerInventory inv, PlayerEntity player) {
        return ContainerTypeRegisters.GEAR_MODIFY.func_221506_a(num, inv);
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