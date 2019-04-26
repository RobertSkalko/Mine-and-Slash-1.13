package com.robertx22.spells.aoe_bomb_proj.bases;

import java.util.List;

import com.robertx22.potion_effects.all.EnergyRegenPotion;
import com.robertx22.potion_effects.all.ManaRegenPotion;
import com.robertx22.spells.bases.projectile.EntityElementalBolt;
import com.robertx22.spells.bases.projectile.Targeting;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.ColoredRedstoneUtils;
import com.robertx22.uncommon.utilityclasses.SoundUtils;
import com.robertx22.uncommon.utilityclasses.WizardryUtilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

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
    public void onUpdate() {

	super.tick();

    }

    @Override
    protected boolean onExpireProc(EntityLivingBase caster) {
	return doEffect(caster);
    }

    public boolean doEffect(EntityLivingBase caster) {

	if (world.isRemote) {

	    SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.6F, 0.6F);

	} else {

	    ColoredRedstoneUtils.SpawnAoeRedstone(element(), this, this.radius(), 300);

	}

	this.world.addParticle(Particles.EXPLOSION, true, this.posX, this.posY, this.posZ, 1, 1, 1);

	boolean hit = false;

	if (!this.world.isRemote && caster != null && effect != null) {

	    List<EntityLivingBase> list = WizardryUtilities.getEntitiesWithinRadius(radius(), this,
		    EntityLivingBase.class);

	    for (int i = 0; i < list.size(); ++i) {
		EntityLivingBase entity = list.get(i);

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

    public void checkOnKill(EntityLivingBase entity) {

	if (entity.isAlive() == false && this.getThrower() != null) {

	    if (this.getBuff().equals(SpellBuffType.Energy_Regen)) {
		this.getThrower().addPotionEffect(new PotionEffect(EnergyRegenPotion.INSTANCE, 400, 2));
	    } else if (this.getBuff().equals(SpellBuffType.Mana_Regen)) {
		this.getThrower().addPotionEffect(new PotionEffect(ManaRegenPotion.INSTANCE, 400, 2));
	    }
	}
    }

    @Override
    protected Targeting.TargetType getTargetType() {
	return Targeting.TargetType.ENEMY;
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