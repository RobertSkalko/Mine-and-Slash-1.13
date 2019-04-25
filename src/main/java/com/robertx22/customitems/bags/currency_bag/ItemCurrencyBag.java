package com.robertx22.customitems.bags.currency_bag;

import com.robertx22.customitems.bags.BaseBagItem;
import com.robertx22.customitems.currency.CurrencyItem;
import com.robertx22.customitems.currency.ICurrencyItemEffect;
import com.robertx22.customitems.ores.ItemOre;
import com.robertx22.mmorpg.Ref;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber
public class ItemCurrencyBag extends BaseBagItem {

    public static final int GUI_NUMBER = 356514;

    @ObjectHolder(Ref.MODID + ":currency_bag")
    public static final Item ITEM = null;

    private static final String TAG_ITEMS = "InvItems";

    public ItemCurrencyBag() {
	super("currency_bag");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemCurrencyBag());

    }

    public boolean IsValidItem(ItemStack stack) {

	return stack.getItem() instanceof ICurrencyItemEffect || stack.getItem() instanceof CurrencyItem
		|| stack.getItem() instanceof ItemOre;
    }

    @Override
    public int GuiNumber() {
	return GUI_NUMBER;
    }

}
