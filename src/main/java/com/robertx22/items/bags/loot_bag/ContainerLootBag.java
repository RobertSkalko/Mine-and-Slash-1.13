package com.robertx22.items.bags.loot_bag;

import com.robertx22.blocks.slots.LootBagSlot;
import com.robertx22.items.bags.BaseContainer;
import com.robertx22.items.bags.BaseInventory;
import com.robertx22.items.bags.BaseSlot;
import com.robertx22.mmorpg.registers.common.ContainerTypeRegisters;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ContainerLootBag extends BaseContainer {

    public ContainerLootBag(int i, PlayerInventory playerInventory) {
        this(i, playerInventory, new InventoryLootBag(new ItemStack(ItemLootBag.ITEM)));
    }

    public ContainerLootBag(int i, PlayerInventory playerInv, BaseInventory basebag) {
        super(ContainerTypeRegisters.LOOT_BAG, i, playerInv, basebag);
    }

    @Override
    public BaseSlot slot(IItemHandler inv, int index, int x, int y) {
        return new LootBagSlot(inv, index, x, y);
    }

}