package com.robertx22.spells.projectile;

import com.robertx22.items.spells.projectile.ItemFireBolt;
import com.robertx22.mmorpg.registers.common.EntityRegister;
import com.robertx22.spells.entities.bases.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellFireBolt extends BaseSpellProjectile {

    static public class EntityFireBolt extends EntityElementalBolt {

        public EntityFireBolt(EntityType<? extends EntityFireBolt> type, World world) {
            super(type, world);
        }

        public EntityFireBolt(World worldIn) {

            super(EntityRegister.FIREBOLT, worldIn);

        }

        @Override
        public Elements element() {
            return Elements.Fire;
        }
    }

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