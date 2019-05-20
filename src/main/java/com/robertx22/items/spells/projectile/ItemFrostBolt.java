package com.robertx22.items.spells.projectile;

import com.robertx22.items.spells.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.projectile.SpellFrostBolt;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemFrostBolt extends BaseSpellItem {

    public ItemFrostBolt() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_frostbolt")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellFrostBolt();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_frostbolt";
    }

}
