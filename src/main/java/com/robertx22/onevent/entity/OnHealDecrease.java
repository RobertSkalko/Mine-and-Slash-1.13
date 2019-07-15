package com.robertx22.onevent.entity;

import com.robertx22.config.ModConfig;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnHealDecrease {

    @SubscribeEvent
    public static void OnEntityHealDecrease(LivingHealEvent event) {

        event.setAmount(event.getAmount() * ModConfig.INSTANCE.Server.NON_MOD_HEAL_MULTI.get()
                .floatValue());

    }

}
