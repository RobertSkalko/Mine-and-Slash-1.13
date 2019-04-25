package com.robertx22.customitems.bags.loot_bag;

import com.robertx22.customitems.bags.BaseBagItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.saveclasses.rune.RuneItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Rune;
import com.robertx22.uncommon.datasaving.Spell;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber
public class ItemLootBag extends BaseBagItem {

    public static final int GUI_NUMBER = 356515;

    @ObjectHolder(Ref.MODID + ":loot_bag")
    public static final Item ITEM = null;

    private static final String TAG_ITEMS = "InvItems";

    public ItemLootBag() {
	super("loot_bag");

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemLootBag());

    }

    public boolean IsValidItem(ItemStack stack) {

	GearItemData gear = Gear.Load(stack);

	if (gear != null) {
	    return true;
	}

	SpellItemData spell = Spell.Load(stack);

	if (spell != null) {
	    return true;
	}

	RuneItemData rune = Rune.Load(stack);
	if (rune != null) {
	    return true;

	}
	return false;
    }

    @Override
    public int GuiNumber() {
	return GUI_NUMBER;
    }

}
