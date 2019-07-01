package com.robertx22.spells.aoe_bomb_proj;

import com.robertx22.items.spells.aoe_bomb_proj.ItemThunderBomb;
import com.robertx22.spells.aoe_bomb_proj.bases.BaseBombSpell;
import com.robertx22.spells.entities.bases.EntityElementalBolt;
import com.robertx22.spells.entities.bases.bomb.EntityThunderBomb;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellThunderBomb extends BaseBombSpell {

    public SpellThunderBomb() {
        super();
    }

    @Override

    public Elements Element() {
        return Elements.Thunder;
    }

    @Override
    public Item SpellItem() {
        return ItemThunderBomb.ITEM;
    }

    @Override
    public String GUID() {
        return "ThunderBomb";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityThunderBomb(world);
    }

}