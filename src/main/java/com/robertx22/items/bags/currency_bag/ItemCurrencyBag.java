package com.robertx22.items.bags.currency_bag;

import com.robertx22.items.bags.BaseBagItem;
import com.robertx22.items.currency.CurrencyItem;
import com.robertx22.items.currency.ICurrencyItemEffect;
import com.robertx22.items.ores.ItemOre;
import com.robertx22.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IInteractionObject;
import net.minecraftforge.registries.ObjectHolder;

public class ItemCurrencyBag extends BaseBagItem {

    public static final String ID = Ref.MODID + ":currency_bag";

    @ObjectHolder(ID)
    public static final Item ITEM = null;

    public ItemCurrencyBag() {
        super(ID);
    }

    public boolean IsValidItem(ItemStack stack) {

        return stack.getItem() instanceof ICurrencyItemEffect || stack.getItem() instanceof CurrencyItem || stack
                .getItem() instanceof ItemOre;
    }

    @Override
    public IInteractionObject getInteractionObject(ItemStack stack) {
        return new InteractCurrencyBag(stack);
    }

}
