package com.robertx22.items.currency;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.PrefixData;
import com.robertx22.uncommon.datasaving.Gear;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber
public class ItemAddPrefix extends CurrencyItem implements ICurrencyItemEffect {

    @Override
    public String GUID() {
        return "add_prefix";
    }

    private static final String name = "add_prefix";

    @ObjectHolder(Ref.MODID + ":add_prefix")
    public static final Item ITEM = null;

    public ItemAddPrefix() {

        super(name);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemAddPrefix());
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        gear.prefix = new PrefixData();
        gear.prefix.RerollFully(gear);

        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);

        return gear != null && gear.prefix == null && !gear.isRuned();
    }

    @Override
    public int Tier() {
        return 10;
    }

    @Override
    public int Rank() {
        return 4;
    }
}