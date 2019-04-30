package com.robertx22.items.spells.projectile;

import com.robertx22.items.gearitems.bases.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.projectile.SpellFireBolt;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid = Ref.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ItemFireBolt extends BaseSpellItem {

    public ItemFireBolt() {
	super();
    }

    @ObjectHolder(Ref.MODID + ":spell_firebolt")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
	return new SpellFireBolt();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemFireBolt());
    }

    @Override
    public String GUID() {
	return Ref.MODID + ":spell_firebolt";
    }

}
