package com.robertx22.spells.aoe_projectile;

import com.robertx22.items.spells.aoe_projectile.ItemFlameExplosion;
import com.robertx22.mmorpg.registers.common.EntityRegister;
import com.robertx22.spells.entities.EntityElementalBolt;
import com.robertx22.spells.entities.EntityElementalBoltAOE;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellFlameExplosion extends BaseAoeSpellProjectile {

    static public class EntityFlameExplosion extends EntityElementalBoltAOE {

        public EntityFlameExplosion(World worldIn) {

            super(EntityRegister.FIREEXPLOSION, worldIn);

        }

        @Override
        public Elements element() {
            return Elements.Fire;
        }

    }

    public SpellFlameExplosion() {
        super();
    }

    @Override
    public Elements Element() {
        return Elements.Fire;
    }

    @Override
    public Item SpellItem() {
        return ItemFlameExplosion.ITEM;
    }

    @Override
    public String GUID() {
        return "FlameExplosion";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityFlameExplosion(world);
    }

}