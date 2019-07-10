package com.robertx22.items.bags.loot_bag;

import com.robertx22.items.bags.SingleContainerBagItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.item_filters.bases.ItemFilterGroup;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.ObjectHolder;

public class ItemLootBag extends SingleContainerBagItem {

    public static final String ID = Ref.MODID + ":loot_bag";

    @ObjectHolder(ID)
    public static final Item ITEM = null;

    public ItemLootBag() {
        super();

    }

    @Override
    public INamedContainerProvider getNamedContainer(ItemStack stack) {
        return new InteractLootBag(stack);
    }

    @Override
    public ItemFilterGroup filterGroup() {
        return ItemFilterGroup.LOOT_BAG;
    }

}
