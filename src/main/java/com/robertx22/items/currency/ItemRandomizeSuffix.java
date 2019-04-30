package com.robertx22.items.currency;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRandomizeSuffix extends CurrencyItem implements ICurrencyItemEffect {
    @Override
    public String GUID() {
	return "randomize_suffix";
    }

    private static final String name = "randomize_suffix";

    @ObjectHolder(Ref.MODID + ":randomize_suffix")
    public static final Item ITEM = null;

    public ItemRandomizeSuffix() {

	super(name);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemRandomizeSuffix());
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {
	GearItemData gear = Gear.Load(stack);
	gear.suffix.RerollFully(gear);
	Gear.Save(stack, gear);

	return stack;
    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {

	GearItemData gear = Gear.Load(stack);

	if (gear.suffix != null) {
	    return true;
	}

	return false;
    }

    @Override
    public int Tier() {
	return 0;
    }

    @Override
    public int Rank() {
	return 0;
    }
}
