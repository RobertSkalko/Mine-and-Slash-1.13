package com.robertx22.items.bags.currency_bag;

import com.robertx22.items.bags.BaseContainer;
import com.robertx22.items.bags.BaseInventory;
import com.robertx22.items.bags.BaseSlot;
import com.robertx22.mmorpg.registers.common.ContainerTypeRegisters;
import com.robertx22.uncommon.item_filters.bases.ItemFilterGroup;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ContainerCurrencyBag extends BaseContainer {

    public ContainerCurrencyBag(int i, PlayerInventory playerInventory) {

        this(i, playerInventory, new InventoryCurrencyBag(new ItemStack(ItemCurrencyBag.ITEM)));

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
            return ItemFilterGroup.ANY_CURRENCY.anyMatchesFilter(stack);
        }

    }

    @Override
    public BaseSlot slot(IItemHandler inv, int index, int x, int y) {
        return new SlotCurrency(inv, index, x, y);
    }

}