package com.robertx22.onevent.combat;

import com.robertx22.mmorpg.Ref;
import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OnBurnStop {

    @SubscribeEvent
    public static void onBurnStop(LivingEvent.LivingUpdateEvent event) {

        if (event.getEntityLiving() instanceof EntityMob) {

            if (event.getEntityLiving().isBurning()) {
                event.getEntityLiving().extinguish();
            }
        }
    }

}
