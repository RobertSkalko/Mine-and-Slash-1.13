package com.robertx22.items.currency;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.interfaces.IRenamed;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemNumberReroll extends CurrencyItem implements ICurrencyItemEffect, IRenamed {
    @Override
    public String GUID() {
        return "number_reroll";
    }

    private static final String name = Ref.MODID + ":currency/number_reroll";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":number_reroll");
    }

    public ItemNumberReroll() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        for (IRerollable rel : gear.GetAllRerollable()) {
            rel.RerollNumbers(gear);
        }
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);

        return gear != null && !gear.isRuned();
    }

    @Override
    public int Tier() {
        return 0;
    }

    @Override
    public int rarity() {
        return 0;
    }
}