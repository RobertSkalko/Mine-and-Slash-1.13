package com.robertx22.items.currency;

import com.robertx22.uncommon.interfaces.IRenamed;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemRandomizeSuffix extends CurrencyItem implements ICurrencyItemEffect, IRenamed {
    @Override
    public String GUID() {
        return "randomize_suffix";
    }

    private static final String name = Ref.MODID + ":currency/randomize_suffix";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":randomize_suffix");
    }

    public ItemRandomizeSuffix() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);
        gear.suffix.RerollFully(gear);
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        if (gear.suffix != null) {
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
        return 0;
    }
}
