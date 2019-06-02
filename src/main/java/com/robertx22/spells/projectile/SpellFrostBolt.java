package com.robertx22.spells.projectile;

import com.robertx22.items.spells.projectile.ItemFrostBolt;
import com.robertx22.mmorpg.registers.common.EntityRegister;
import com.robertx22.spells.entities.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellFrostBolt extends BaseSpellProjectile {
    static public class EntityFrostBolt extends EntityElementalBolt {

        public EntityFrostBolt(World worldIn) {

            super(EntityRegister.FROSTBOLT, worldIn);

        }

        @Override
        public Elements element() {
            return Elements.Water;
        }
    }

    public SpellFrostBolt() {
        super();
    }

    @Override
    public Elements Element() {
        return Elements.Water;
    }

    @Override
    public Item SpellItem() {
        return ItemFrostBolt.ITEM;
    }

    @Override
    public String GUID() {
        return "FrostBolt";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityFrostBolt(world);
    }

}