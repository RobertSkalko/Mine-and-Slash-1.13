package com.robertx22.items.bags;

import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.items.ItemSingle;
import com.robertx22.uncommon.item_filters.bases.ItemFilterGroup;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public abstract class ItemBaseBag extends Item {

    public ItemBaseBag() {
        super(new ItemSingle().group(CreativeTabs.MyModTab));
    }

    public abstract ItemFilterGroup filterGroup();

    public abstract INamedContainerProvider getNamedContainer(ItemStack stack);

    public int size = 9 * 6;

    public abstract IItemHandler getInventory(ItemStack bag, ItemStack stack);
}
