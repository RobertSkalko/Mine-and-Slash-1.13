package com.robertx22.items.currency;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.ChaosStatsData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.interfaces.IRenamed;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemChaosOrb extends CurrencyItem implements ICurrencyItemEffect, IRenamed {
    @Override
    public String GUID() {
        return "chaos_orb";
    }

    private static final String name = Ref.MODID + ":currency/chaos_orb";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":chaos_orb");
    }

    public ItemChaosOrb() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);
        gear.chaosStats = new ChaosStatsData();
        gear.chaosStats.RerollFully(gear);
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        if (gear.chaosStats == null && !gear.isRuned()) {
            return true;
        }

        return false;
    }

    @Override
    public int Tier() {
        return 3;
    }

    @Override
    public int rarity() {
        return 1;
    }
}
