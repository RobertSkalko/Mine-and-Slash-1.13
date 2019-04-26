package com.robertx22.customitems.currency;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.customitems.ItemDefault;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.interfaces.ITiered;
import com.robertx22.uncommon.interfaces.IWeighted;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.Tooltip;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public abstract class CurrencyItem extends Item implements IWeighted, ITiered {

    public static HashSet<CurrencyItem> ITEMS = new HashSet<CurrencyItem>();

    public abstract String GUID();

    public CurrencyItem(String name) {
	super(new ItemDefault());

	RegisterItemUtils.RegisterItemName(this, name);

	ITEMS.add(this);

    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
	    ITooltipFlag flagIn) {

	Tooltip.add(CLOC.tooltip(GUID()), tooltip);
	Tooltip.add(CLOC.lore(GUID()), tooltip);

    }

    public abstract int Rank();

    @Override
    public int Weight() {

	if (Rank() == 0) {
	    return ModConfig.RarityWeightConfig.CURRENCY.COMMON_WEIGHT;
	} else if (Rank() == 1) {
	    return ModConfig.RarityWeightConfig.CURRENCY.UNCOMMON_WEIGHT;
	} else if (Rank() == 2) {
	    return ModConfig.RarityWeightConfig.CURRENCY.RARE_WEIGHT;
	} else if (Rank() == 3) {
	    return ModConfig.RarityWeightConfig.CURRENCY.EPIC_WEIGHT;
	} else if (Rank() == 4) {
	    return ModConfig.RarityWeightConfig.CURRENCY.LEGENDARY_WEIGHT;
	} else if (Rank() == 5) {
	    return ModConfig.RarityWeightConfig.CURRENCY.MYTHICAL_WEIGHT;
	}
	return 0;

    }

}
