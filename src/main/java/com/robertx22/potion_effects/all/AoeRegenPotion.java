package com.robertx22.potion_effects.all;

import com.robertx22.mmorpg.Ref;
import com.robertx22.potion_effects.SpellPotionBase;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

public class AoeRegenPotion extends SpellPotionBase {

    public static final AoeRegenPotion INSTANCE = new AoeRegenPotion();

    private AoeRegenPotion() {
        // boolean isBadEffectIn, int liquidColorIn
        super(false, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

    }

    @Override
    public String GUID() {
        return "aoe_regen";
    }

    @Override
    public void doEffect(Entity applier, Entity caster, LivingEntity target,
                         int amplifier) {

    }

    @Override
    public void performEffectEverySetTime(LivingEntity entity, int amplifier) {

        UnitData data = Load.Unit(entity);

        for (LivingEntity en : this.getEntitiesAround(entity, 3F)) {

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
