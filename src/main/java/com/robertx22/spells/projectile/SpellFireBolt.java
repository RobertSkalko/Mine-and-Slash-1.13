package com.robertx22.spells.projectile;

import com.robertx22.items.spells.projectile.ItemFireBolt;
import com.robertx22.spells.entities.bases.EntityElementalBolt;
import com.robertx22.spells.entities.bases.proj.EntityFireBolt;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellFireBolt extends BaseSpellProjectile {

    public SpellFireBolt() {
        super();
    }

    @Override
    public Elements Element() {
        return Elements.Fire;
    }

    @Override
    public Item SpellItem() {
        return ItemFireBolt.ITEM;
    }

    @Override
    public String GUID() {
        return "FireBolt";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityFireBolt(world);
    }

}