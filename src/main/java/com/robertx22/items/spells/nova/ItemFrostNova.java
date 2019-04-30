package com.robertx22.items.spells.nova;

import com.robertx22.items.gearitems.bases.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.nova.SpellFrostNova;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid = Ref.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ItemFrostNova extends BaseSpellItem {

    public ItemFrostNova() {
	super();
    }

    @ObjectHolder(Ref.MODID + ":spell_frost_nova")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
	return new SpellFrostNova();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemFrostNova());
    }

    @Override
    public String GUID() {
	return Ref.MODID + ":spell_frost_nova";
    }

}
