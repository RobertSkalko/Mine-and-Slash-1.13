package com.robertx22.onevent.combat;

import com.robertx22.config.ModConfig;
import com.robertx22.spells.bases.MyDamageSource;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class OnHurt {

    /**
     * If damage is from my source, leave the value, otherwise decrease it (this
     * makes my damage source the best one)
     *
     * @param event
     */
    @SubscribeEvent
    public static void OnLivingHurt(LivingHurtEvent event) {

        if (event.getSource() instanceof MyDamageSource) {
            return;
        }

        // mobs take much less damage from any source other than my mods. This is
        // required or else there's no point in getting legendary weapons if a diamond
        // sword more damage
        if (event.getSource() != null && event.getSource()
                .getTrueSource() instanceof EntityLivingBase && Load.hasUnit(event.getEntityLiving())) {
            if (event.getSource().isExplosion()) {
                event.setAmount(event.getAmount() * ModConfig.INSTANCE.Server.get().MOB_ENVIRONMENT_DAMAGE_MULTI
                        .get()
                        .floatValue());
                return;
            } else {
                event.setAmount(event.getAmount() * ModConfig.INSTANCE.Server.get().NON_MOD_DAMAGE_MULTI
                        .get()
                        .floatValue());
                return;
            }
        }

    }
}