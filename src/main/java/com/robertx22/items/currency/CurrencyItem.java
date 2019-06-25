package com.robertx22.items.currency;

import com.robertx22.config.ModConfig;
import com.robertx22.database.IGUID;
import com.robertx22.db_lists.Rarities;
import com.robertx22.items.ItemDefault;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.interfaces.*;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.Tooltip;
import com.robertx22.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class CurrencyItem extends Item implements IGUID, IWeighted, ITiered, IAutoLocMultiLore, IAutoLocDesc, IAutoLocName {

    public static List<CurrencyItem> ITEMS = new ArrayList<>();

    public static TextFormatting nameColor = TextFormatting.RED;

    public abstract String GUID();

    public CurrencyItem(String name) {
        super(new ItemDefault().maxStackSize(64));

        RegisterItemUtils.RegisterItemName(this, name);

        ITEMS.add(this);

    }

    @Override
    public AutoLocGroup locDescGroup() {
        return AutoLocGroup.Currency_Items;
    }

    @Override
    public String locDescLangFileGUID() {
        return this.getRegistryName().toString() + ".desc";
    }

    @Override
    public AutoLocGroup locLoresGroup() {
        return AutoLocGroup.Currency_Items;
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Currency_Items;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        if (this instanceof IAutoLocMultiLore) {
            IAutoLocMultiLore auto = (IAutoLocMultiLore) this;
            for (ITextComponent comp : auto.getComponents()) {
                tooltip.add(Styles.GREENCOMP()
                        .appendText("'")
                        .appendSibling(comp)
                        .appendText("'"));
            }
        }

        //Tooltip.add("", tooltip);

        if (this instanceof IAutoLocDesc) {
            IAutoLocDesc auto = (IAutoLocDesc) this;
            tooltip.add(Styles.YELLOWCOMP().appendSibling(auto.locDesc()));
        }
        //Tooltip.add("", tooltip);

        Tooltip.add("", tooltip);

        tooltip.add(TooltipUtils.tier(this.Tier()));
        tooltip.add(TooltipUtils.rarity(Rarities.Items.get(this.rarity())));

        Tooltip.add("", tooltip);

        Tooltip.add(Styles.BLUECOMP()
                .appendSibling(CLOC.tooltip("item_modifiable_in_station")), tooltip);

    }

    public abstract int rarity();

    @Override
    public int Weight() {

        if (rarity() == 0) {
            return ModConfig.INSTANCE.RarityWeightConfig.CURRENCY.COMMON_WEIGHT.get();
        } else if (rarity() == 1) {
            return ModConfig.INSTANCE.RarityWeightConfig.CURRENCY.UNCOMMON_WEIGHT.get();
        } else if (rarity() == 2) {
            return ModConfig.INSTANCE.RarityWeightConfig.CURRENCY.RARE_WEIGHT.get();
        } else if (rarity() == 3) {
            return ModConfig.INSTANCE.RarityWeightConfig.CURRENCY.EPIC_WEIGHT.get();
        } else if (rarity() == 4) {
            return ModConfig.INSTANCE.RarityWeightConfig.CURRENCY.LEGENDARY_WEIGHT.get();
        } else if (rarity() == 5) {
            return ModConfig.INSTANCE.RarityWeightConfig.CURRENCY.MYTHICAL_WEIGHT.get();
        }
        return 0;

    }

}
