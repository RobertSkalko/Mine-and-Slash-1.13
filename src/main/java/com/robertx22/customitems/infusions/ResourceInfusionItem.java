package com.robertx22.customitems.infusions;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.LifeOnHitFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.LifestealFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaOnHitFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.mmorpg.Ref;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber
public class ResourceInfusionItem extends BaseInfusionItem {

    public ResourceInfusionItem() {
	super(name);

    }

    private static final String name = "resource_infusion";

    @ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ResourceInfusionItem());
    }

    @Override
    public List<StatMod> weaponInfusions() {
	return Arrays.asList(new ManaOnHitFlat(), new LifeOnHitFlat(), new LifestealFlat());
    }

    @Override
    public List<StatMod> armorInfusions() {
	return Arrays.asList(new ManaRegenFlat(), new EnergyRegenFlat(), new HealthRegenFlat());
    }

    @Override
    public List<StatMod> jewerlyInfusions() {
	return Arrays.asList(new ManaFlat(), new HealthRegenFlat());
    }

    @Override
    public String GUID() {
	return name;
    }

}
