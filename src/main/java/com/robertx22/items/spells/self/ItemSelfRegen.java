package com.robertx22.items.spells.self;

import com.robertx22.items.gearitems.bases.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.self.SpellSelfRegen;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemSelfRegen extends BaseSpellItem {

    public ItemSelfRegen() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_self_regen")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellSelfRegen();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_self_regen";
    }

}
