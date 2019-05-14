package com.robertx22.potion_effects.all;

import com.robertx22.mmorpg.Ref;
import com.robertx22.potion_effects.SpellPotionBase;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class HealthRegenPotion extends SpellPotionBase {

    public static final HealthRegenPotion INSTANCE = new HealthRegenPotion();

    private HealthRegenPotion() {
        // boolean isBadEffectIn, int liquidColorIn
        super(false, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, "self_regen"));

    }

    @Override
    public ResourceLocation getIconTexture() {
        return new ResourceLocation(Ref.MODID, "textures/status_effects/regen.png");
    }

    private static void apply(EntityLivingBase entity) {

    }

    @Override
    public boolean canSelfCast() {
        return true;
    }

    @Override
    public void doEffect(Entity applier, Entity caster, EntityLivingBase target,
                         int amplifier) {

        apply(target);

    }

    @Override
    public void performEffectEverySetTime(EntityLivingBase entity, int amplifier) {

        try {

            if (entity.world.isRemote) {
                ParticleUtils.spawnHealParticles(entity, 3);
            } else {
                UnitData data = Load.Unit(entity);
                data.heal(entity, amplifier);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int performEachXTicks() {
        return 40;
    }
}
