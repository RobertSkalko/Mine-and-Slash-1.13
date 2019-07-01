package com.robertx22.spells.entities.bases.aoe;

import com.robertx22.mmorpg.registers.common.EntityRegister;
import com.robertx22.spells.entities.bases.EntityElementalBoltAOE;
import com.robertx22.uncommon.enumclasses.Elements;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class EntityAcidExplosion extends EntityElementalBoltAOE {
    public EntityAcidExplosion(EntityType<? extends EntityAcidExplosion> type,
                               World world) {
        super(type, world);
    }

    public EntityAcidExplosion(World worldIn) {

        super(EntityRegister.ACIDEXPLOSION, worldIn);

    }

    public EntityAcidExplosion(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.ACIDEXPLOSION, world);

    }

    @Override
    public Elements element() {
        return Elements.Nature;
    }

}
