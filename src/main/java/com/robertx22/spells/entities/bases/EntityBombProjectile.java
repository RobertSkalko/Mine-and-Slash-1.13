package com.robertx22.spells.entities.bases;

import com.robertx22.potion_effects.all.EnergyRegenPotion;
import com.robertx22.potion_effects.all.ManaRegenPotion;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.ElementalParticleUtils;
import com.robertx22.uncommon.utilityclasses.SoundUtils;
import com.robertx22.uncommon.utilityclasses.WizardryUtilities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.init.Particles;
import net.minecraft.util.SoundEvents;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public abstract class EntityBombProjectile extends EntityElementalBolt {

    @Override
    public double radius() {
        return 2.5D;
    }

    public EntityBombProjectile(EntityType<?> type, World worldIn) {
        super(type, worldIn);

        this.setDeathTime(60);
        this.setAirProcTime(40);
        this.setDoExpireProc(true);
        this.setNoGravity(true); // REQUIRED

    }

    @Override
    public void tick() {

        super.tick();

    }

    @Override
    protected boolean onExpireProc(LivingEntity caster) {
        return doEffect(caster);
    }

    public boolean doEffect(LivingEntity caster) {

        if (world.isRemote) {

            SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.6F, 0.6F);

        } else {

            ElementalParticleUtils.SpawnAoeParticle(element(), this, this.radius(), 300);

        }

        this.world.addParticle(Particles.EXPLOSION, true, this.posX, this.posY, this.posZ, 1, 1, 1);

        boolean hit = false;

        if (!this.world.isRemote && caster != null && effect != null) {

            List<LivingEntity> list = WizardryUtilities.getEntitiesWithinRadius(radius(), this, LivingEntity.class);

            for (int i = 0; i < list.size(); ++i) {
                LivingEntity entity = list.get(i);

                if (Load.hasUnit(entity)) {
                    effect.Activate(data, entity);

                    checkOnKill(entity);

                    hit = true;
                }

            }
            return hit;
        }

        return false;

    }

    public void checkOnKill(LivingEntity entity) {

        if (entity.isAlive() == false && this.getThrower() != null) {

            if (this.getBuff().equals(SpellBuffType.Energy_Regen)) {
                this.getThrower()
                        .addPotionEffect(new EffectInstance(EnergyRegenPotion.INSTANCE, 400, 2));
            } else if (this.getBuff().equals(SpellBuffType.Mana_Regen)) {
                this.getThrower()
                        .addPotionEffect(new EffectInstance(ManaRegenPotion.INSTANCE, 400, 2));
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {

        switch (result.type) {
            case BLOCK:
                this.motionX = 0;
                this.motionY = 0;
                this.motionZ = 0;

                break;
            case ENTITY:
                break;
            case MISS:
                break;
        }

    }

}