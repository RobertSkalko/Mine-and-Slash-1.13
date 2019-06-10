package com.robertx22.spells.projectile;

import com.robertx22.items.spells.projectile.ItemAcidBolt;
import com.robertx22.mmorpg.registers.common.EntityRegister;
import com.robertx22.spells.entities.bases.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class SpellAcidBolt extends BaseSpellProjectile {
    static public class EntityAcidBolt extends EntityElementalBolt {
        public EntityAcidBolt(EntityType<? extends EntityAcidBolt> type, World world) {
            super(type, world);
        }

        public EntityAcidBolt(World worldIn) {

            super(EntityRegister.ACIDBOLT, worldIn);

        }

        public EntityAcidBolt(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
            super(EntityRegister.ACIDBOLT, world);

        }

        @Override
        public Elements element() {
            return Elements.Nature;
        }

    }

    public SpellAcidBolt() {
        super();
    }

    @Override
    public Elements Element() {
        return Elements.Nature;
    }

    @Override
    public Item SpellItem() {
        return ItemAcidBolt.ITEM;
    }

    @Override
    public String GUID() {
        return "AcidBolt";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityAcidBolt(world);
    }

}