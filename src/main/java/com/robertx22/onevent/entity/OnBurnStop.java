package com.robertx22.onevent.entity;

import net.minecraft.entity.monster.MonsterEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class OnBurnStop {

    @SubscribeEvent
    public static void onBurnStop(LivingEvent.LivingUpdateEvent event) {

        if (event.getEntityLiving() instanceof MonsterEntity) {

            if (event.getEntityLiving().isBurning()) {
                event.getEntityLiving().extinguish();
            }

        }
    }

}


