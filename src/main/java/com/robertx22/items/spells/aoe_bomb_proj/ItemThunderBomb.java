package com.robertx22.items.spells.aoe_bomb_proj;

import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.aoe_bomb_proj.SpellThunderBomb;
import com.robertx22.spells.bases.BaseSpell;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemThunderBomb extends BaseBombitem {

    public ItemThunderBomb() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_thunder_bomb")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellThunderBomb();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_thunder_bomb";
    }

}
