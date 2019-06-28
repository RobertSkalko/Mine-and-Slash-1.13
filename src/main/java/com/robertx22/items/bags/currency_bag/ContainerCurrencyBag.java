package com.robertx22.items.bags.currency_bag;

import com.robertx22.blocks.slots.handlerslots.CurrencySlotHandler;
import com.robertx22.items.bags.BaseContainer;
import com.robertx22.items.bags.BaseInventory;
import com.robertx22.items.bags.BaseSlot;
import com.robertx22.mmorpg.registers.common.ContainerTypeRegisters;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ContainerCurrencyBag extends BaseContainer {

    public ContainerCurrencyBag(int i, PlayerInventory playerInventory) {

        this(i, playerInventory, new BaseInventory(new ItemStack(ItemCurrencyBag.ITEM)));

    }

    public ContainerCurrencyBag(int i, PlayerInventory playerInv, BaseInventory basebag) {
        super(ContainerTypeRegisters.CURRENCY_BAG, i, playerInv, basebag);
    }

    @Override
    public BaseSlot slot(IItemHandler inv, int index, int x, int y) {
        return new CurrencySlotHandler(inv, index, x, y);
    }

}