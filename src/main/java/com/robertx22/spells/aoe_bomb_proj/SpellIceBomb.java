package com.robertx22.spells.aoe_bomb_proj;

import com.robertx22.items.spells.aoe_bomb_proj.ItemIceBomb;
import com.robertx22.mmorpg.registers.common.EntityRegister;
import com.robertx22.spells.aoe_bomb_proj.bases.BaseBombSpell;
import com.robertx22.spells.entities.EntityBombProjectile;
import com.robertx22.spells.entities.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpellIceBomb extends BaseBombSpell {

    public SpellIceBomb() {
        super();
    }

    static public class EntityIceBomb extends EntityBombProjectile {

        public EntityIceBomb(World worldIn) {
            super(EntityRegister.FROSTBOMB, worldIn);

        }

        @Override
        public Elements element() {
            return Elements.Water;
        }
    }

    @Override
    public Elements Element() {
        return Elements.Water;
    }

    @Override
    public Item SpellItem() {
        return ItemIceBomb.ITEM;
    }

    @Override
    public String GUID() {
        return "IceBomb";
    }

    @Override
    public EntityElementalBolt projectile(World world) {
        return new EntityIceBomb(world);
    }

}