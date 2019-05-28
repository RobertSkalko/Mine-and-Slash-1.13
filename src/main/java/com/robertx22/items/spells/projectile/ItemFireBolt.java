package com.robertx22.items.spells.projectile;

import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.projectile.SpellFireBolt;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemFireBolt extends BaseBoltItem {

    public ItemFireBolt() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_firebolt")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellFireBolt();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_firebolt";
    }

}
