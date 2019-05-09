package com.robertx22.items.bags.map_bag;

import com.robertx22.items.bags.BaseBagItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.uncommon.datasaving.Map;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IInteractionObject;
import net.minecraftforge.registries.ObjectHolder;

public class ItemMapBag extends BaseBagItem {

    public static final String ID = Ref.MODID + ":map_bag";

    @ObjectHolder(ID)
    public static final Item ITEM = null;

    public ItemMapBag() {
        super(ID);

    }

    @Override
    public IInteractionObject getInteractionObject(ItemStack stack) {
        return new InteractMapBag(stack);
    }

    public boolean IsValidItem(ItemStack stack) {

        MapItemData map = Map.Load(stack);

        if (map != null) {
            return true;

        }

        return false;
    }

}
