package com.robertx22.items.currency;

import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber
public class ItemLevelUpGear extends CurrencyItem implements ICurrencyItemEffect {
    @Override
    public String GUID() {
	return "item_levelup";
    }

    private static final String name = "item_levelup";

    @ObjectHolder(Ref.MODID + ":item_levelup")
    public static final Item ITEM = null;

    public ItemLevelUpGear() {

	super(name);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemLevelUpGear());
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {
	GearItemData gear = Gear.Load(stack);
	gear.level++;
	gear.timesLeveledUp++;
	Gear.Save(stack, gear);

	return stack;
    }

    public static final int MAXIMUM_LEVEL_UPS = 10;

    @Override
    public int Rank() {
	return 2;
    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {
	GearItemData gear = Gear.Load(stack);

	return gear != null && gear.timesLeveledUp < MAXIMUM_LEVEL_UPS
		&& gear.level < ModConfig.Server.MAXIMUM_PLAYER_LEVEL;

    }

    @Override
    public int Tier() {
	return 5;
    }

}