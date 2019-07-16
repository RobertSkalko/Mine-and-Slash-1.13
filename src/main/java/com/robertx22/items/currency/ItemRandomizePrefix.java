package com.robertx22.items.currency;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.interfaces.IRenamed;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemRandomizePrefix extends CurrencyItem implements ICurrencyItemEffect, IRenamed {
    @Override
    public String GUID() {
        return "randomize_prefix";
    }

    private static final String name = Ref.MODID + ":currency/randomize_prefix";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":randomize_prefix");
    }

    public ItemRandomizePrefix() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);
        gear.prefix.RerollFully(gear);
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public boolean canItemBeModifiedPROTECTED(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        if (gear.prefix != null) {
            return true;
        }

        return false;
    }

    @Override
    public int Tier() {
        return 0;
    }

    @Override
    public int rarity() {
        return 0;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("There is always a better choice");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Orb Of Ever-Changing Prefix";
    }

    @Override
    public String locDescForLangFile() {
        return "Re-rolls prefix";
    }
}