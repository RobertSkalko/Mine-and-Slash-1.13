package com.robertx22.spells.nova;

import com.robertx22.items.spells.nova.ItemThunderNova;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

public class SpellThunderNova extends BaseNovaSpell {

    @Override
    public String GUID() {
        return "spell_thunder_nova";
    }

    @Override
    public Elements Element() {
        return Elements.Thunder;
    }

    @Override
    public Item SpellItem() {
        return ItemThunderNova.ITEM;
    }

}
