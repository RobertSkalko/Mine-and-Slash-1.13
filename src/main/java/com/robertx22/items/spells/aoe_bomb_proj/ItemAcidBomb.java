package com.robertx22.items.spells.aoe_bomb_proj;

import com.robertx22.items.gearitems.bases.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.aoe_bomb_proj.SpellAcidBomb;
import com.robertx22.spells.bases.BaseSpell;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemAcidBomb extends BaseSpellItem {

    public ItemAcidBomb() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_acid_bomb")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellAcidBomb();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_acid_bomb";
    }

}
