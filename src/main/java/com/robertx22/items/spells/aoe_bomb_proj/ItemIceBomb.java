package com.robertx22.items.spells.aoe_bomb_proj;

import com.robertx22.items.gearitems.bases.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.aoe_bomb_proj.SpellIceBomb;
import com.robertx22.spells.bases.BaseSpell;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemIceBomb extends BaseSpellItem {

    public ItemIceBomb() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_ice_bomb")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellIceBomb();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_ice_bomb";
    }

}
