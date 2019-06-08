package com.robertx22.spells.aoe_bomb_proj;

import com.robertx22.items.spells.aoe_bomb_proj.ItemAcidBomb;
import com.robertx22.mmorpg.registers.common.EntityRegister;
import com.robertx22.spells.aoe_bomb_proj.bases.BaseBombSpell;
import com.robertx22.spells.entities.bases.EntityBombProjectile;
import com.robertx22.spells.entities.bases.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellAcidBomb extends BaseBombSpell {

    public SpellAcidBomb() {
        super();
    }

    static public class EntityAcidBomb extends EntityBombProjectile {
        public EntityAcidBomb(EntityType<? extends EntityAcidBomb> type, World world) {
            super(type, world);
        }

        public EntityAcidBomb(World worldIn) {
            super(EntityRegister.ACIDBOMB, worldIn);

        }

        @Override
        public Elements element() {
            return Elements.Nature;
        }

    }

    @Override
    public Elements Element() {
        return Elements.Nature;
    }

    @Override
    public Item SpellItem() {
        return ItemAcidBomb.ITEM;
    }

    @Override
    public String GUID() {
        return "AcidBomb";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityAcidBomb(world);
    }

}