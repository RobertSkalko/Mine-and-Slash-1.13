package com.robertx22.items.spells.nova;

import com.robertx22.items.gearitems.bases.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.nova.SpellPoisonNova;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemPoisonNova extends BaseSpellItem {

    public ItemPoisonNova() {
        super();

    }

    @ObjectHolder(Ref.MODID + ":spell_poison_nova")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellPoisonNova();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemPoisonNova());
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_poison_nova";
    }

}
