package com.robertx22.potion_effects.all;

import com.robertx22.mmorpg.Ref;
import com.robertx22.potion_effects.SpellPotionBase;
import com.robertx22.spells.self.SpellInstantHeal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class CleansePoisonPotion extends SpellPotionBase {

    public static final CleansePoisonPotion INSTANCE = new CleansePoisonPotion();

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Potion> event) {
        event.getRegistry().register(INSTANCE);
    }

    private CleansePoisonPotion() {
        // boolean isBadEffectIn, int liquidColorIn
        super(false, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, "effect.cleanse_poison"));

    }

    @Override
    public ResourceLocation getIconTexture() {
        return new ResourceLocation(Ref.MODID, "textures/status_effects/cleanse_poison.png");
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
                SpellInstantHeal.spawnHealParticles(entity, 1);
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