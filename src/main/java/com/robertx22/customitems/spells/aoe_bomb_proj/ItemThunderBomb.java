package com.robertx22.customitems.spells.aoe_bomb_proj;

import com.robertx22.customitems.gearitems.bases.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.aoe_bomb_proj.SpellThunderBomb;
import com.robertx22.spells.bases.BaseSpell;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber
public class ItemThunderBomb extends BaseSpellItem {

    public ItemThunderBomb() {
	super();
    }

    @ObjectHolder(Ref.MODID + ":spell_Thunder_bomb")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
	return new SpellThunderBomb();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemThunderBomb());
    }

    @Override
    public String GUID() {
	return Ref.MODID + ":spell_Thunder_bomb";
    }

}
