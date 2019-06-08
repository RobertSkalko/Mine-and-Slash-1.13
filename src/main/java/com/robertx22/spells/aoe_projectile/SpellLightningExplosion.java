package com.robertx22.spells.aoe_projectile;

import com.robertx22.items.spells.aoe_projectile.ItemLightningExplosion;
import com.robertx22.mmorpg.registers.common.EntityRegister;
import com.robertx22.spells.entities.bases.EntityElementalBolt;
import com.robertx22.spells.entities.bases.EntityElementalBoltAOE;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellLightningExplosion extends BaseAoeSpellProjectile {
    static public class EntityLightningExplosion extends EntityElementalBoltAOE {
        public EntityLightningExplosion(
                EntityType<? extends EntityLightningExplosion> type, World world) {
            super(type, world);
        }

        public EntityLightningExplosion(World worldIn) {

            super(EntityRegister.THUNDEREXPLOSION, worldIn);

        }

        @Override
        public Elements element() {
            return Elements.Thunder;
        }

    }

    public SpellLightningExplosion() {
        super();
    }

    @Override
    public Elements Element() {
        return Elements.Thunder;
    }

    @Override
    public Item SpellItem() {
        return ItemLightningExplosion.ITEM;
    }

    @Override
    public String GUID() {
        return "LightningExplosion";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityLightningExplosion(world);
    }

}