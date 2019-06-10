package com.robertx22.items.bags.map_bag;

import com.robertx22.items.bags.BaseContainer;
import com.robertx22.items.bags.BaseInventory;
import com.robertx22.items.bags.BaseSlot;
import com.robertx22.mmorpg.registers.ContainerTypeRegisters;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ContainerMapBag extends BaseContainer {

    public ContainerMapBag(int i, PlayerInventory playerInventory) {
        this(i, playerInventory, new InventoryMapBag(ItemStack.EMPTY));
    }

    public ContainerMapBag(int i, PlayerInventory playerInv, BaseInventory basebag) {
        super(ContainerTypeRegisters.MAP_BAG, i, playerInv, basebag);
    }

    public class SlotMapBag extends BaseSlot {

        public SlotMapBag(IItemHandler itemHandler, int index, int xPosition,
                          int yPosition) {
            super(itemHandler, index, xPosition, yPosition);

        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            return new ItemMapBag().IsValidItem(stack);
        }

        @Override
        public BaseSlot newObject(IItemHandler inv, int index, int x, int y) {
            return new SlotMapBag(inv, index, x, y);
        }

    }

    @Override
    public BaseSlot slot(IItemHandler inv, int index, int x, int y) {
        return new SlotMapBag(inv, index, x, y);
    }

}