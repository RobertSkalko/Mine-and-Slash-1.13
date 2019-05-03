package com.robertx22.potion_effects.all;

import com.robertx22.mmorpg.Ref;
import com.robertx22.potion_effects.SpellPotionBase;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class EnergyRegenPotion extends SpellPotionBase {

    public static final EnergyRegenPotion INSTANCE = new EnergyRegenPotion();

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Potion> event) {
        event.getRegistry().register(INSTANCE);
    }

    private EnergyRegenPotion() {
        // boolean isBadEffectIn, int liquidColorIn
        super(false, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, "effect.energy_regen"));

    }

    @Override
    public ResourceLocation getIconTexture() {
        return new ResourceLocation(Ref.MODID, "textures/status_effects/energy_regen.png");
    }

    @Override
    public boolean canSelfCast() {
        return true;
    }

    @Override
    public void performEffectEverySetTime(EntityLivingBase entity, int amplifier) {

        try {

            if (entity.world.isRemote) {
                ParticleUtils.spawnEnergyRestoreParticles(entity, 5);
            } else {
                UnitData data = Load.Unit(entity);
                data.restoreEnergy(amplifier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int performEachXTicks() {
        return 40;
    }

    @Override
    public void doEffect(Entity applier, Entity caster, EntityLivingBase target,
                         int amplifier) {

    }
}
