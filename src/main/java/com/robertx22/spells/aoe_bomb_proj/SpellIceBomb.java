package com.robertx22.spells.aoe_bomb_proj;

import com.robertx22.items.spells.aoe_bomb_proj.ItemIceBomb;
import com.robertx22.mmorpg.registers.common.EntityRegister;
import com.robertx22.spells.aoe_bomb_proj.bases.BaseBombSpell;
import com.robertx22.spells.entities.bases.EntityBombProjectile;
import com.robertx22.spells.entities.bases.EntityElementalBolt;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class SpellIceBomb extends BaseBombSpell {

    public SpellIceBomb() {
        super();
    }

    @OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
    static public class EntityIceBomb extends EntityBombProjectile {
        public EntityIceBomb(EntityType<? extends EntityIceBomb> type, World world) {
            super(type, world);
        }

        public EntityIceBomb(World worldIn) {
            super(EntityRegister.FROSTBOMB, worldIn);

        }

        public EntityIceBomb(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
            super(EntityRegister.FROSTBOMB, world);

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