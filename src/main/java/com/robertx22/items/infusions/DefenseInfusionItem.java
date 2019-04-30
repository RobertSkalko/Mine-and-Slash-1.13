package com.robertx22.items.infusions;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.DodgeFlat;
import com.robertx22.database.stats.stat_mods.flat.MajorArmorFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DefenseInfusionItem extends BaseInfusionItem {

    public DefenseInfusionItem() {
        super(name);

    }

    private static final String name = "defense_infusion";

    @ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new DefenseInfusionItem());
    }

    @Override
    public List<StatMod> weaponInfusions() {
        return Arrays.asList(new DodgeFlat(), new MajorArmorFlat());
    }

    @Override
    public List<StatMod> armorInfusions() {
        return Arrays.asList(new ArmorFlat(), new FireResistFlat(), new WaterResistFlat(), new ThunderResistFlat(), new NatureResistFlat(), new DodgeFlat());
    }

    @Override
    public List<StatMod> jewerlyInfusions() {
        return armorInfusions();
    }

    @Override
    public String GUID() {
        return name;
    }

}
