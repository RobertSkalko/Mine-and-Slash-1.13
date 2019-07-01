package com.robertx22.spells.entities.bases.bomb;

import com.robertx22.mmorpg.registers.common.EntityRegister;
import com.robertx22.spells.entities.bases.EntityBombProjectile;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class EntityFireBomb extends EntityBombProjectile {
    public EntityFireBomb(EntityType<? extends EntityFireBomb> type, World world) {
        super(type, world);
    }

    public EntityFireBomb(World worldIn) {
        super(EntityRegister.FIREBOMB, worldIn);

    }

    public EntityFireBomb(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.FIREBOMB, world);

    }

    @Override
    public Elements element() {
        return Elements.Fire;
    }

}
