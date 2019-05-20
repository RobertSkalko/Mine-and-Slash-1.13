package com.robertx22.items.spells.self;

import com.robertx22.items.spells.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.self.SpellInstantHeal;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

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

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_instantheal";
    }

}
