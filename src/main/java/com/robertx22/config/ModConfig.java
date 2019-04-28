package com.robertx22.config;

import com.robertx22.mmorpg.Ref;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ModConfig {

    // public static ClientContainer Client = new ClientContainer(new
    // ForgeConfigSpec.Builder());

    public static EntityConfigs EntityTypeConfig = new EntityConfigs();

    public static RarityDropratesConfig.RarityWeights RarityWeightConfig = new RarityDropratesConfig.RarityWeights();

    public static ServerContainer Server = new ServerContainer();

    public static DropRatesContainer DropRates = new DropRatesContainer();

    @Mod.EventBusSubscriber
    private static class EventHandler {

        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent event) {

            if (event.getModID().equals(Ref.MODID)) {
                System.out.println("Syncing Mine and Slash Config");
            }
        }

    }

}
