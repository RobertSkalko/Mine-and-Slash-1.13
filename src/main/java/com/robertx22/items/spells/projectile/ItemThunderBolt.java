package com.robertx22.items.spells.projectile;

import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.projectile.SpellThunderBolt;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemThunderBolt extends BaseBoltItem {

    public ItemThunderBolt() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_thunderbolt")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellThunderBolt();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_thunderbolt";
    }

}
