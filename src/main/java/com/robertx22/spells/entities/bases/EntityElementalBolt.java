package com.robertx22.spells.entities.bases;

import com.robertx22.spells.bases.BaseSpellEffect;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.uncommon.effectdatas.SpellBuffEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.ElementalParticleUtils;
import com.robertx22.uncommon.utilityclasses.SoundUtils;
import com.robertx22.uncommon.utilityclasses.Utilities;
import com.robertx22.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityElementalBolt extends EntityBaseProjectile {

    @Override
    public ItemStack getItem() {
        return new ItemStack(this.element().projectileItem);
    }

    protected BaseSpellEffect effect;
    protected DamageData data;

    public abstract Elements element();

    public EntityElementalBolt(EntityType<? extends LivingEntity> type, World worldIn) {
        super(type, worldIn);

    }

    @Override
    public double radius() {
        return 0.5D;
    }

    protected void entityInit() {
    }

    public void SetReady(BaseSpellEffect effect, DamageData data) {
        this.effect = effect;
        this.data = data;

    }

    public void ifDamageKilledEnemy(LivingEntity enemy) {
        if (enemy.getHealth() <= 0) {

        }
    }

    public List<LivingEntity> entitiesHit = new ArrayList();

    public LivingEntity getEntityHit(RayTraceResult result, Entity projectile) {

        EntityRayTraceResult enres = null;

        if (result instanceof EntityRayTraceResult) {
            enres = (EntityRayTraceResult) result;
        }

        if (enres != null && enres.getEntity() instanceof LivingEntity) {
            return (LivingEntity) enres.getEntity();
        }

        List<LivingEntity> entities = Utilities.getEntitiesWithinRadius(0.3D, projectile, LivingEntity.class);

        if (entities.size() > 0) {
            return entities.get(0);
        }

        return null;

    }

    @Override
    protected void onImpact(RayTraceResult result) {

        LivingEntity entityHit = getEntityHit(result, this);
        if (entityHit == this.getThrower()) {
            return;
        }

        if (entityHit instanceof LivingEntity && effect != null && data != null) {
            if (world.isRemote) {
                SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_HURT, 0.4F, 0.9F);
            }

            if (!entitiesHit.contains(entityHit)) {
                effect.Activate(data, entityHit);

                ifDamageKilledEnemy(entityHit);
                entitiesHit.add(entityHit);
            }

        } else {
            if (world.isRemote) {
                SoundUtils.playSound(this, SoundEvents.BLOCK_STONE_HIT, 0.7F, 0.9F);
            }
        }

        if (!this.world.isRemote) {
            if (this.getBuff()
                    .equals(SpellBuffType.Ghost_Projectile) == false) { // spell buff to go through all
                // mobs in the way and damage
                // them all
                this.world.setEntityState(this, (byte) 3);
                this.remove();
            }

        }
    }

    int ticks = 0;

    @Override
    public void tick() {

        super.tick();

        if (world.isRemote) {

            ticks++;
            if (ticks > 1) {
                ticks = 0;
                ElementalParticleUtils.SpawnAoeParticle(element(), this, 0.15F, 15);
            }

        }

    }

    public void SpawnAndShoot(BaseSpellEffect effect, DamageData data,
                              LivingEntity caster) {

        this.spellType = data.spellItem.GetSpell().Type();

        SpellBuffEffect spelleffect = new SpellBuffEffect(caster, this);
        spelleffect.Activate();

        this.ignoreEntity = caster;
        this.thrower = caster;
        Vec3d look = caster.getLookVec();

        SetReady(effect, data);
        setPosition(caster.posX + look.x, caster.posY + look.y + 1.3, caster.posZ + look.z);
        shoot(caster, caster.rotationPitch, caster.rotationYaw, 0.0F, 1.3F, 0.5F); // start velocity

        WorldUtils.spawnEntity(world, this);
    }

}