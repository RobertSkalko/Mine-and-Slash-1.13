package com.robertx22.items.currency;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.interfaces.IRenamed;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemAddSecondaryStat extends CurrencyItem implements ICurrencyItemEffect, IRenamed {

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":add_secondary_stat");
    }

    @Override
    public String GUID() {
        return name;
    }

    private static final String name = Ref.MODID + ":currency/add_secondary_stat";

    public ItemAddSecondaryStat() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        gear.secondaryStats.AddStat(gear);
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public boolean canItemBeModifiedPROTECTED(ItemStack stack, ItemStack Currency) {
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
    public int rarity() {
        return 1;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("More power is always good, right?");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Crystal Of Legend";
    }

    @Override
    public String locDescForLangFile() {
        return "Add another secondary stat";
    }
}