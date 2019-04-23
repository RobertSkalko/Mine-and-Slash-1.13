package com.robertx22.customitems.infusions.upgrade;

import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@EventBusSubscriber
public class WondrousUpgradeInfusion extends BaseUpgradeInfusion {

    public WondrousUpgradeInfusion() {
	super(name);
    }

    private static final String name = "wondrous_upgrade_infusion";

    @ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new WondrousUpgradeInfusion());
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);
    }

    @Override
    public int Tier() {
	return 12;
    }

    @Override
    public float critOnSuccessChance() {
	return 20F;
    }

    @Override
    public float bonusSuccessChance() {
	return 15F;
    }

    @Override
    public float majorFailureChance() {
	return 3F;
    }

    @Override
    public String GUID() {
	return name;
    }

    @Override
    public int Rank() {
	return 3;
    }

}
