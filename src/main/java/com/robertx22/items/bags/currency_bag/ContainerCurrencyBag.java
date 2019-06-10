package com.robertx22.items.bags.currency_bag;

import com.robertx22.items.bags.BaseContainer;
import com.robertx22.items.bags.BaseInventory;
import com.robertx22.items.bags.BaseSlot;
import com.robertx22.mmorpg.registers.ContainerTypeRegisters;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ContainerCurrencyBag extends BaseContainer {

    public ContainerCurrencyBag(int i, PlayerInventory playerInventory) {
        this(i, playerInventory, new InventoryCurrencyBag(ItemStack.EMPTY));
    }

    public ContainerCurrencyBag(int i, PlayerInventory playerInv, BaseInventory basebag) {
        super(ContainerTypeRegisters.CURRENCY_BAG, i, playerInv, basebag);
    }

    public class SlotCurrency extends BaseSlot {

        public SlotCurrency(IItemHandler itemHandler, int index, int xPosition,
                            int yPosition) {
            super(itemHandler, index, xPosition, yPosition);

        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            return new ItemCurrencyBag().IsValidItem(stack);
        }

        @Override
        public BaseSlot newObject(IItemHandler inv, int index, int x, int y) {
            return new SlotCurrency(inv, index, x, y);
        }

    }

    @Override
    public BaseSlot slot(IItemHandler inv, int index, int x, int y) {
        return new SlotCurrency(inv, index, x, y);
    }

}