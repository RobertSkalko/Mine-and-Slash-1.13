package com.robertx22.mmorpg.config;

import java.util.ArrayList;

import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.config.RarityDropratesConfig.RarityWeights;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ModConfig {

    public static ClientContainer Client = new ClientContainer(new ForgeConfigSpec.Builder());

    public static EntityConfigs EntityTypeConfig = new EntityConfigs();

    public static StatConfig BasePlayerStats = new StatConfig();

    public static RarityWeights RarityWeightConfig = new RarityWeights();

    public static DimensionsConfig Dimensions = new DimensionsConfig();

    public static ServerContainer Server = new ServerContainer();

    public static DropRatesContainer DropRates = new DropRatesContainer();

    public static class DimensionsConfig {

	public DimensionsContainer getAll() {

	    ArrayList<DimensionConfigs> list = new ArrayList<DimensionConfigs>();

	    list.add(overworld);
	    list.add(nether);
	    list.add(end);
	    list.add(extra1);
	    list.add(extra2);
	    list.add(extra3);
	    list.add(extra4);
	    list.add(extra5);

	    return new DimensionsContainer(list);

	}

	public DimensionConfigs overworld = DimensionConfigs.Overworld();

	public DimensionConfigs nether = DimensionConfigs.Nether();

	public DimensionConfigs end = DimensionConfigs.End();

	public DimensionConfigs default_dim = DimensionConfigs.Overworld();

	public DimensionConfigs extra1 = DimensionConfigs.DefaultExtra();
	public DimensionConfigs extra2 = DimensionConfigs.DefaultExtra();

	public DimensionConfigs extra3 = DimensionConfigs.DefaultExtra();

	public DimensionConfigs extra4 = DimensionConfigs.DefaultExtra();

	public DimensionConfigs extra5 = DimensionConfigs.DefaultExtra();
    }

    @Mod.EventBusSubscriber
    private static class EventHandler {

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent event) {

	    if (event.getModID().equals(Ref.MODID)) {
		// ConfigManager.sync(Ref.MODID, Config.Type.INSTANCE);

		System.out.println("Syncing Config");
	    }
	}

    }

}
