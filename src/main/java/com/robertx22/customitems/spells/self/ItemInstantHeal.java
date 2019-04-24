package com.robertx22.customitems.spells.self;

import com.robertx22.customitems.gearitems.bases.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.self.SpellInstantHeal;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber
public class ItemInstantHeal extends BaseSpellItem {

    public ItemInstantHeal() {
	super();
    }

    @ObjectHolder(Ref.MODID + ":spell_instantheal")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
	return new SpellInstantHeal();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemInstantHeal());
    }

    @Override
    public String GUID() {
	return Ref.MODID + ":spell_instantheal";
    }

}
