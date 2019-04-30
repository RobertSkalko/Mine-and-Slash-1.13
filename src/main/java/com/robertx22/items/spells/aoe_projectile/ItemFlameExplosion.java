package com.robertx22.items.spells.aoe_projectile;

import com.robertx22.items.gearitems.bases.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.aoe_projectile.SpellFlameExplosion;
import com.robertx22.spells.bases.BaseSpell;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemFlameExplosion extends BaseSpellItem {

    public ItemFlameExplosion() {
	super();
    }

    @ObjectHolder(Ref.MODID + ":spell_flameexplosion")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
	return new SpellFlameExplosion();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemFlameExplosion());
    }

    @Override
    public String GUID() {
	return Ref.MODID + ":spell_flameexplosion";
    }

}
