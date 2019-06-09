package com.robertx22.blocks.map_device;

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

public class ContainerMapDevice extends BaseTileContainer {

    // These store cache values, used by the server to only update the client side
    // tile entity when values have changed
    private int[] cachedFields;

    private final int HOTBAR_SLOT_COUNT = 9;
    private final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    // slot index is the unique index for all slots in this container i.e. 0 - 35
    // for invPlayer then 36 - 49 for tileInventoryFurnace
    private final int VANILLA_FIRST_SLOT_INDEX = 0;
    private final int TIER_SLOT_INDEX = 0;
    private final int LEVEL_SLOT_INDEX = 1;
    private final int MAP_SLOT_INDEX = 2;
    private final int START_SLOT_INDEX = 3;

    public ContainerMapDevice(int i, PlayerInventory playerInventory) {
        super(ContainerTypeRegisters.MAP_DEVICE, i);
        final int SLOT_X_SPACING = 18;
        final int SLOT_Y_SPACING = 18;
        final int HOTBAR_XPOS = 8;
        final int HOTBAR_YPOS = 183;
        // Add the players hotbar to the gui - the [xpos, ypos] location of each item
        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
            int slotNumber = x;
            addSlot(new Slot(playerInventory, slotNumber, HOTBAR_XPOS + SLOT_X_SPACING * x, HOTBAR_YPOS));
        }

        final int PLAYER_INVENTORY_XPOS = 8;
        final int PLAYER_INVENTORY_YPOS = 125;
        // Add the rest of the players inventory to the gui
        for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
            for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
                int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
                int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
                int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
                addSlot(new Slot(playerInventory, slotNumber, xpos, ypos));
            }
        }

    }

    public ContainerMapDevice(int num, PlayerInventory invPlayer, TileMapDevice tile) {
        super(ContainerTypeRegisters.MAP_DEVICE, num);

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
        final int TIER_X = 26;
        final int TIER_Y = 85;
        addSlot(new NormalSlot(tile, TIER_SLOT_INDEX, TIER_X, TIER_Y));

        final int LEVEL_X = 134;
        final int LEVEL_Y = 85;
        addSlot(new NormalSlot(tile, LEVEL_SLOT_INDEX, LEVEL_X, LEVEL_Y));

        final int MAP_X = 81;
        final int MAP_Y = 28;
        addSlot(new NormalSlot(tile, MAP_SLOT_INDEX, MAP_X, MAP_Y));

        final int START_X = 80;
        final int START_Y = 99;
        addSlot(new NormalSlot(tile, START_SLOT_INDEX, START_X, START_Y));

    }

    // Checks each tick to make sure the player is still able to access the
    // inventory and if not closes the gui
    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return true;
    }

    // shift click logic
    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int sourceSlotIndex) {
        return ItemStack.EMPTY;
    }

    /* Client Synchronization */
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

    }

    // SlotSmeltableInput is a slot for input items
    public class NormalSlot extends Slot {
        public NormalSlot(IInventory inventoryIn, int index, int xPosition,
                          int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        // if this function returns false, the player won't be able to insert the given
        // item into this slot
        @Override
        public boolean isItemValid(ItemStack stack) {
            return true;
        }
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Map Device");
    }

    @Nullable
    @Override
    public Container createMenu(int num, PlayerInventory inv, PlayerEntity player) {
        return ContainerTypeRegisters.MAP_DEVICE.func_221506_a(num, inv);
    }
}