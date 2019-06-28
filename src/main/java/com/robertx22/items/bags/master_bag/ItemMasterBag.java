package com.robertx22.items.bags.master_bag;

import com.robertx22.items.bags.BaseBagItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.item_filters.bases.ItemFilterGroup;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.ObjectHolder;

public class ItemMasterBag extends BaseBagItem {

    public static final String ID = Ref.MODID + ":master_bag";

    @ObjectHolder(ID)
    public static final Item ITEM = null;

    public ItemMasterBag() {
        super(ID);

        this.size *= ContainerMasterBag.ItemType.values().length;

    }

    @Override
    public ItemFilterGroup filterGroup() {
        return ItemFilterGroup.ANY_MASTER_BAG;
    }

    @Override
    public INamedContainerProvider getNamedContainer(ItemStack stack) {
        return new NamedMasterBag(stack, ContainerMasterBag.ItemType.GEAR);
    }

}
