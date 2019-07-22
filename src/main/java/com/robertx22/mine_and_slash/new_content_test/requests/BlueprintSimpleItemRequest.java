package com.robertx22.mine_and_slash.new_content_test.requests;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ILevel;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class BlueprintSimpleItemRequest extends BaseBlueprintRequest {

    @Store
    public int amount = 1;

    @Store
    public String itemRegistryName;

    @Store
    public int level;

    @Store
    public int tier;

    @Store
    public int rarity;

    // public abstract BaseBlueprintItemRequest random(BlueprintItemData data);
    @Override
    public boolean matches(ItemStack stack) {

        if (stack.getCount() >= amount) {
            if (this.itemRegistryName.equals(stack.getItem()
                    .getRegistryName()
                    .toString())) {

                Item item = stack.getItem();

                boolean matches = true;

                if (matches) {
                    if (item instanceof ITiered) {
                        ITiered part = (ITiered) item;
                        matches = part.Tier() >= tier;
                    }
                }
                if (matches) {
                    if (item instanceof ILevel) {
                        ILevel part = (ILevel) item;
                        matches = part.getLevel() >= level;
                    }
                }
                if (matches) {
                    if (item instanceof IRarity) {
                        IRarity part = (IRarity) item;
                        matches = part.getRarityRank() >= rarity;
                    }
                }

                return matches;
            }
        }

        return false;
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        return list;
    }
}
