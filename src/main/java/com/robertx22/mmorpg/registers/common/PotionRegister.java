package com.robertx22.mmorpg.registers.common;

import com.robertx22.mmorpg.Ref;
import com.robertx22.potion_effects.all.*;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PotionRegister {

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Potion> event) {

        IForgeRegistry<Potion> reg = event.getRegistry();

        reg.register(AoeRegenPotion.INSTANCE);
        reg.register(CleansePoisonPotion.INSTANCE);
        reg.register(EnergyRegenPotion.INSTANCE);
        reg.register(HealthRegenPotion.INSTANCE);
        reg.register(ManaRegenPotion.INSTANCE);
    }

}