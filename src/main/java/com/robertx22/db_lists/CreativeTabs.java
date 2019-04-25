package com.robertx22.db_lists;

import com.robertx22.customitems.currency.ItemChaosOrb;
import com.robertx22.customitems.gearitems.weapons.ItemSword;
import com.robertx22.mmorpg.Ref;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreativeTabs {

    public static final ItemGroup MyModTab = new ItemGroup(Ref.MODID + "_main") {

	@Override
	public ItemStack createIcon() {
	    return new ItemStack(ItemSword.Items.get(5));
	}

    };

    public static final ItemGroup UniqueItems = new ItemGroup(Ref.MODID + "_uniques") {

	@Override
	public ItemStack createIcon() {
	    return new ItemStack(ItemSword.Items.get(2));
	}

    };

    public static final ItemGroup CurrencyTab = new ItemGroup(Ref.MODID + "_currency") {

	@Override
	public ItemStack createIcon() {
	    return new ItemStack(ItemChaosOrb.ITEM);
	}

    };

}