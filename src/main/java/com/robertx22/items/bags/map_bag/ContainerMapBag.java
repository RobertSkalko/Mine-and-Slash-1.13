package com.robertx22.items.bags.map_bag;

import com.robertx22.blocks.slots.MapSlot;
import com.robertx22.items.bags.BaseContainer;
import com.robertx22.items.bags.BaseInventory;
import com.robertx22.items.bags.BaseSlot;
import com.robertx22.mmorpg.registers.common.ContainerTypeRegisters;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ContainerMapBag extends BaseContainer {

    public ContainerMapBag(int i, PlayerInventory playerInventory) {
        this(i, playerInventory, new InventoryMapBag(new ItemStack(ItemMapBag.ITEM)));
    }

    public ContainerMapBag(int i, PlayerInventory playerInv, BaseInventory basebag) {
        super(ContainerTypeRegisters.MAP_BAG, i, playerInv, basebag);
    }

    @Override
    public BaseSlot slot(IItemHandler inv, int index, int x, int y) {
        return new MapSlot(inv, index, x, y);
    }

}