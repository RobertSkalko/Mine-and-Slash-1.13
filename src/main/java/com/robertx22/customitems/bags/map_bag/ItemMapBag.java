package com.robertx22.customitems.bags.map_bag;

import com.robertx22.customitems.bags.BaseBagItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.uncommon.datasaving.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber
public class ItemMapBag extends BaseBagItem {

    public static final int GUI_NUMBER = 356516;

    @ObjectHolder(Ref.MODID + ":map_bag")
    public static final Item ITEM = null;

    private static final String TAG_ITEMS = "InvItems";

    public ItemMapBag() {
	super("map_bag");

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemMapBag());
    }

    public boolean IsValidItem(ItemStack stack) {

	MapItemData map = Map.Load(stack);

	if (map != null) {
	    return true;

	}

	return false;
    }

    @Override
    public int GuiNumber() {
	return GUI_NUMBER;
    }

}
