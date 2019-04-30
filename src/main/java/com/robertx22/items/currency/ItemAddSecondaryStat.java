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

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemAddSecondaryStat extends CurrencyItem implements ICurrencyItemEffect {
    @Override
    public String GUID() {
        return name;
    }

    private static final String name = "add_secondary_stat";

    @ObjectHolder(Ref.MODID + ":add_secondary_stat")
    public static final Item ITEM = null;

    public ItemAddSecondaryStat() {

        super(name);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemAddSecondaryStat());
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        gear.secondaryStats.AddStat(gear);
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);

        if (gear.secondaryStats != null && gear.secondaryStats.AddedStat == false && !gear
                .isRuned()) {
            return true;
        }

        return false;
    }

    @Override
    public int Tier() {
        return 0;
    }

    @Override
    public int Rank() {
        return 1;
    }
}