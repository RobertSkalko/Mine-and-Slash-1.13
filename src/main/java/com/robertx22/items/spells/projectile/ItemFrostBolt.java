package com.robertx22.items.spells.projectile;

import com.robertx22.items.gearitems.bases.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.projectile.SpellFrostBolt;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemFrostBolt extends BaseSpellItem {

    public ItemFrostBolt() {
	super();
    }

    @ObjectHolder(Ref.MODID + ":spell_frostbolt")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
	return new SpellFrostBolt();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemFrostBolt());
    }

    @Override
    public String GUID() {
	return Ref.MODID + ":spell_frostbolt";
    }

}
