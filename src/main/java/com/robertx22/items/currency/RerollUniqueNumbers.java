package com.robertx22.items.currency;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RerollUniqueNumbers extends CurrencyItem implements ICurrencyItemEffect {
    @Override
    public String GUID() {
        return "reroll_unique_numbers";
    }

    private static final String name = "reroll_unique_numbers";

    @ObjectHolder(Ref.MODID + ":reroll_unique_numbers")
    public static final Item ITEM = null;

    public RerollUniqueNumbers() {

        super(name);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new RerollUniqueNumbers());
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        gear.uniqueStats.RerollNumbers(gear);

        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);

        return gear != null && gear.uniqueStats != null && gear.isUnique;
    }

    @Override
    public int Tier() {
        return 14;
    }

    @Override
    public int Rank() {
        return 3;
    }
}