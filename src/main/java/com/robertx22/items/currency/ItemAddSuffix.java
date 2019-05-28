package com.robertx22.items.currency;

import com.robertx22.mmorpg.IRenamed;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.SuffixData;
import com.robertx22.uncommon.datasaving.Gear;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemAddSuffix extends CurrencyItem implements ICurrencyItemEffect, IRenamed {

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":add_suffix");
    }

    @Override
    public String GUID() {
        return "add_suffix";
    }

    private static final String name = Ref.MODID + ":currency/add_suffix";

    public ItemAddSuffix() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        gear.suffix = new SuffixData();
        gear.suffix.RerollFully(gear);

        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);

        return gear != null && gear.suffix == null && !gear.isRuned();
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
