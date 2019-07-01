package com.robertx22.spells.projectile;

import com.robertx22.items.spells.projectile.ItemFrostBolt;
import com.robertx22.mmorpg.registers.common.EntityRegister;
import com.robertx22.spells.entities.bases.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class SpellFrostBolt extends BaseSpellProjectile {
    @OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
    static public class EntityFrostBolt extends EntityElementalBolt {
        public EntityFrostBolt(EntityType<? extends EntityFrostBolt> type, World world) {
            super(type, world);
        }

        public EntityFrostBolt(World worldIn) {

            super(EntityRegister.FROSTBOLT, worldIn);

        }

        public EntityFrostBolt(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
            super(EntityRegister.FROSTBOLT, world);

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