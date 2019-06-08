package com.robertx22.spells.projectile;

import com.robertx22.items.spells.projectile.ItemThunderBolt;
import com.robertx22.mmorpg.registers.common.EntityRegister;
import com.robertx22.spells.entities.bases.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellThunderBolt extends BaseSpellProjectile {
    static public class EntityThunderBolt extends EntityElementalBolt {

        public EntityThunderBolt(EntityType<? extends EntityThunderBolt> type,
                                 World world) {
            super(type, world);
        }

        public EntityThunderBolt(World worldIn) {

            super(EntityRegister.THUNDERBOLT, worldIn);

        }

        @Override
        public Elements element() {
            return Elements.Thunder;
        }

    }

    public SpellThunderBolt() {
        super();
    }

    @Override
    public Elements Element() {
        return Elements.Thunder;
    }

    @Override
    public Item SpellItem() {
        return ItemThunderBolt.ITEM;
    }

    @Override
    public String GUID() {
        return "ThunderBolt";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityThunderBolt(world);
    }

}