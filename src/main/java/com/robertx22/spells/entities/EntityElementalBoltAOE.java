package com.robertx22.spells.entities;

import com.robertx22.uncommon.utilityclasses.ElementalParticleUtils;
import com.robertx22.uncommon.utilityclasses.SoundUtils;
import com.robertx22.uncommon.utilityclasses.WizardryUtilities;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public abstract class EntityElementalBoltAOE extends EntityElementalBolt {

    public EntityElementalBoltAOE(EntityType<?> type, World worldIn) {
        super(type, worldIn);

    }

    @Override
    public double radius() {
        return 3D;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

        if (world.isRemote) {

            SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.4F, 0.5F);

        } else {

            ElementalParticleUtils.SpawnAoeParticle(element(), this, radius(), 500);

        }
        if (effect != null && data != null) {

            List<EntityLivingBase> entities = WizardryUtilities.getEntitiesWithinRadius(radius(), this, EntityLivingBase.class);

            if (entities != null) {
                for (EntityLivingBase entity : entities) {
                    effect.Activate(data, entity);
                }
            }
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }

    }
}
