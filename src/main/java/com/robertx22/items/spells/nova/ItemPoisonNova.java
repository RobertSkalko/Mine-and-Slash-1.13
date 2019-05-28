package com.robertx22.items.spells.nova;

import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.nova.SpellPoisonNova;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemPoisonNova extends BaseItemNova {

    public ItemPoisonNova() {
        super();

    }

    @ObjectHolder(Ref.MODID + ":spell_poison_nova")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellPoisonNova();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_poison_nova";
    }

}
