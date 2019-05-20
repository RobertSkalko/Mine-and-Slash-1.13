package com.robertx22.items.spells.nova;

import com.robertx22.items.spells.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.nova.SpellFireNova;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemFireNova extends BaseSpellItem {

    public ItemFireNova() {
        super();

    }

    @ObjectHolder(Ref.MODID + ":spell_fire_nova")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellFireNova();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_fire_nova";
    }

}
