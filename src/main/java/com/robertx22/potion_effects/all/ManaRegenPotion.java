package com.robertx22.potion_effects.all;

import com.robertx22.mmorpg.Ref;
import com.robertx22.potion_effects.SpellPotionBase;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class ManaRegenPotion extends SpellPotionBase {

    public static final ManaRegenPotion INSTANCE = new ManaRegenPotion();

    private ManaRegenPotion() {
        // boolean isBadEffectIn, int liquidColorIn
        super(false, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

    }

    @Override
    public String GUID() {
        return "mana_regen";
    }

    @Override
    public boolean canSelfCast() {
        return true;
    }

    @Override
    public void doEffect(Entity applier, Entity caster, EntityLivingBase target,
                         int amplifier) {

    }

    @Override
    public void performEffectEverySetTime(EntityLivingBase entity, int amplifier) {

        try {

            if (entity.world.isRemote) {
                ParticleUtils.spawnManaRestoreParticles(entity, 5);
            } else {
                UnitData data = Load.Unit(entity);
                data.restoreMana(amplifier);

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
