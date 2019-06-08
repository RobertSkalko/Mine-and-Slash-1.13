package com.robertx22.spells.aoe_projectile;

import com.robertx22.items.spells.aoe_projectile.ItemFrostExplosion;
import com.robertx22.mmorpg.registers.common.EntityRegister;
import com.robertx22.spells.entities.bases.EntityElementalBolt;
import com.robertx22.spells.entities.bases.EntityElementalBoltAOE;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellFrostExplosion extends BaseAoeSpellProjectile {
    static public class EntityFrostExplosion extends EntityElementalBoltAOE {
        public EntityFrostExplosion(EntityType<? extends EntityFrostExplosion> type,
                                    World world) {
            super(type, world);
        }

        public EntityFrostExplosion(World worldIn) {

            super(EntityRegister.FROSTEXPLOSION, worldIn);

        }

        @Override
        public Elements element() {
            return Elements.Water;
        }

    }

    public SpellFrostExplosion() {
        super();
    }

    @Override
    public Elements Element() {
        return Elements.Water;
    }

    @Override
    public Item SpellItem() {
        return ItemFrostExplosion.ITEM;
    }

    @Override
    public String GUID() {
        return "FrostExplosion";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityFrostExplosion(world);
    }

}