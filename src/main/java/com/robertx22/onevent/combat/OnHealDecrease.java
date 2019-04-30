package com.robertx22.onevent.combat;

import com.robertx22.config.ModConfig;
import com.robertx22.mmorpg.Ref;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OnHealDecrease {

    @SubscribeEvent
    public static void OnEntityHealDecrease(LivingHealEvent event) {

        if (event.getEntityLiving() instanceof IMob || event.getEntityLiving() instanceof EntityMob || event
                .getEntityLiving() instanceof EntityPlayer) {

            event.setAmount(event.getAmount() * ModConfig.INSTANCE.Server.NON_MOD_HEAL_MULTI
                    .get()
                    .floatValue());

        }
    }
}
