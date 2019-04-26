package com.robertx22.items.infusions.upgrade;

import com.robertx22.mmorpg.Ref;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber
public class SuperiorUpgradeInfusion extends BaseUpgradeInfusion {

    public SuperiorUpgradeInfusion() {
	super(name);

    }

    private static final String name = "superior_upgrade_infusion";

    @ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new SuperiorUpgradeInfusion());
    }

    @Override
    public int Tier() {
	return 9;
    }

    @Override
    public float critOnSuccessChance() {
	return 10F;
    }

    @Override
    public float bonusSuccessChance() {
	return 5F;
    }

    @Override
    public float majorFailureChance() {
	return 4F;
    }

    @Override
    public String GUID() {
	return name;
    }

    @Override
    public int Rank() {
	return 2;
    }

}
