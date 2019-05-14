package com.robertx22.potion_effects.all;

import com.robertx22.mmorpg.Ref;
import com.robertx22.potion_effects.SpellPotionBase;
import com.robertx22.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.util.ResourceLocation;

public class CleansePoisonPotion extends SpellPotionBase {

    public static final CleansePoisonPotion INSTANCE = new CleansePoisonPotion();

    private CleansePoisonPotion() {
        // boolean isBadEffectIn, int liquidColorIn
        super(false, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

    }

    @Override
    public String GUID() {
        return "cleanse_poison";
    }

    @Override
    public void doEffect(Entity applier, Entity caster, EntityLivingBase target,
                         int amplifier) {

    }

    @Override
    public void performEffectEverySetTime(EntityLivingBase entity, int amplifier) {

        try {

            if (entity.world.isRemote) {
                ParticleUtils.spawnHealParticles(entity, 1);
            } else {

                if (entity.getActivePotionEffect(MobEffects.POISON) != null) {
                    entity.removePotionEffect(MobEffects.POISON);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int performEachXTicks() {
        return 20;
    }
}