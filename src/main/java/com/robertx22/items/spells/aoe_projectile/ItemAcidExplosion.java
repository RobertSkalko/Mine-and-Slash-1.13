package com.robertx22.items.spells.aoe_projectile;

import com.robertx22.items.spells.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.aoe_projectile.SpellAcidExplosion;
import com.robertx22.spells.bases.BaseSpell;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemAcidExplosion extends BaseSpellItem {

    public ItemAcidExplosion() {
        super();
    }

    @ObjectHolder(Ref.MODID + ":spell_acidexplosion")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
        return new SpellAcidExplosion();
    }

    @Override
    public String GUID() {
        return Ref.MODID + ":spell_acidexplosion";
    }

}
