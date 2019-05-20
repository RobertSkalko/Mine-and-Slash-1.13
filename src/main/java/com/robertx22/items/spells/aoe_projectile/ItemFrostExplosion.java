package com.robertx22.items.spells.aoe_projectile;

import com.robertx22.items.spells.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.aoe_projectile.SpellFrostExplosion;
import com.robertx22.spells.bases.BaseSpell;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemFrostExplosion extends BaseSpellItem {

    public ItemFrostExplosion() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_frostexplosion")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellFrostExplosion();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_frostexplosion";
    }

}
