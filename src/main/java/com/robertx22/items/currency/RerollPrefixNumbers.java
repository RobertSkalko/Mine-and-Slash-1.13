package com.robertx22.items.currency;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.ObjectHolder;

public class RerollPrefixNumbers extends CurrencyItem implements ICurrencyItemEffect {
    @Override
    public String GUID() {
        return "reroll_prefix_numbers";
    }

    private static final String name = "reroll_prefix_numbers";

    @ObjectHolder(Ref.MODID + ":reroll_prefix_numbers")
    public static final Item ITEM = null;

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
    public int Rank() {
        return 3;
    }
}