package com.robertx22.items.currency;

import com.robertx22.config.ModConfig;
import com.robertx22.items.ItemDefault;
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

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public abstract class CurrencyItem extends Item implements IWeighted, ITiered {

    public static HashSet<CurrencyItem> ITEMS = new HashSet<CurrencyItem>();

    public abstract String GUID();

    public CurrencyItem(String name) {
        super(new ItemDefault());

        RegisterItemUtils.RegisterItemName(this, name);

        ITEMS.add(this);

    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        Tooltip.add(CLOC.tooltip(GUID()), tooltip);
        Tooltip.add(CLOC.lore(GUID()), tooltip);

    }

    public abstract int Rank();

    @Override
    public int Weight() {

        if (Rank() == 0) {
            return ModConfig.INSTANCE.RarityWeightConfig.get().CURRENCY.get().COMMON_WEIGHT
                    .get();
        } else if (Rank() == 1) {
            return ModConfig.INSTANCE.RarityWeightConfig.get().CURRENCY.get().UNCOMMON_WEIGHT
                    .get();
        } else if (Rank() == 2) {
            return ModConfig.INSTANCE.RarityWeightConfig.get().CURRENCY.get().RARE_WEIGHT.get();
        } else if (Rank() == 3) {
            return ModConfig.INSTANCE.RarityWeightConfig.get().CURRENCY.get().EPIC_WEIGHT.get();
        } else if (Rank() == 4) {
            return ModConfig.INSTANCE.RarityWeightConfig.get().CURRENCY.get().LEGENDARY_WEIGHT
                    .get();
        } else if (Rank() == 5) {
            return ModConfig.INSTANCE.RarityWeightConfig.get().CURRENCY.get().MYTHICAL_WEIGHT
                    .get();
        }
        return 0;

    }

}
