package com.robertx22.items.spells.nova;

import com.robertx22.items.gearitems.bases.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.nova.SpellThunderNova;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemThunderNova extends BaseSpellItem {

    public ItemThunderNova() {
        super();

    }

    @ObjectHolder(Ref.MODID + ":spell_thunder_nova")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellThunderNova();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_thunder_nova";
    }

}
