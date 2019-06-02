package com.robertx22.spells.aoe_projectile;

import com.robertx22.items.spells.aoe_projectile.ItemAcidExplosion;
import com.robertx22.mmorpg.registers.common.EntityRegister;
import com.robertx22.spells.entities.EntityElementalBolt;
import com.robertx22.spells.entities.EntityElementalBoltAOE;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellAcidExplosion extends BaseAoeSpellProjectile {

    static public class EntityAcidExplosion extends EntityElementalBoltAOE {

        public EntityAcidExplosion(World worldIn) {

            super(EntityRegister.ACIDEXPLOSION, worldIn);

        }

        @Override
        public Elements element() {
            return Elements.Nature;
        }

    }

    public SpellAcidExplosion() {
        super();
    }

    @Override
    public Elements Element() {
        return Elements.Nature;
    }

    @Override
    public Item SpellItem() {
        return ItemAcidExplosion.ITEM;
    }

    @Override
    public String GUID() {
        return "AcidExplosion";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityAcidExplosion(world);
    }

}