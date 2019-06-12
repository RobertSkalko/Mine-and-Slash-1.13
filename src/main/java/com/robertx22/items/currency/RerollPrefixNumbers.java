package com.robertx22.items.currency;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.interfaces.IRenamed;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class RerollPrefixNumbers extends CurrencyItem implements ICurrencyItemEffect, IRenamed {
    @Override
    public String GUID() {
        return "reroll_prefix_numbers";
    }

    private static final String name = Ref.MODID + ":currency/reroll_prefix_numbers";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":reroll_prefix_numbers");
    }

    public RerollPrefixNumbers() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        gear.prefix.RerollNumbers(gear);

        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);

        return gear != null && gear.prefix != null;
    }

    @Override
    public int Tier() {
        return 15;
    }

    @Override
    public int rarity() {
        return 3;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("I command you to change!");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Orb Of Prefix Blessing";
    }

    @Override
    public String locDescForLangFile() {
        return "Re-rolls numbers of a prefix";
    }
}