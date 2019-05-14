package com.robertx22.potion_effects.all;

import com.robertx22.mmorpg.Ref;
import com.robertx22.potion_effects.SpellPotionBase;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class AoeRegenPotion extends SpellPotionBase {

    public static final AoeRegenPotion INSTANCE = new AoeRegenPotion();

    private AoeRegenPotion() {
        // boolean isBadEffectIn, int liquidColorIn
        super(false, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, "aoe_regen"));

    }

    @Override
    public ResourceLocation getIconTexture() {
        return new ResourceLocation(Ref.MODID, "textures/status_effects/aoe_regen.png");
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

        UnitData data = Load.Unit(entity);

        for (EntityLivingBase en : this.getEntitiesAround(entity, 3F)) {

            if (en.world.isRemote) {
                ParticleUtils.spawnHealParticles(en, 3);
            } else {
                Load.Unit(en).heal(en, (int) data.getUnit().healthData().Value / 50);

            }
        }

    }

    @Override
    public int performEachXTicks() {
        return 40;
    }
}
