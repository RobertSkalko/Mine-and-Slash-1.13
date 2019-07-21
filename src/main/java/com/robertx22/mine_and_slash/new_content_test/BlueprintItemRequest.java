package com.robertx22.mine_and_slash.new_content_test;

import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ILevel;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Storable
public class BlueprintItemRequest {

    @Store
    public int amount = 1;

    @Store
    public String itemRegistryName;

    @Store
    public boolean needsDataItem = false;

    @Store
    public int level;

    @Store
    public int tier;

    @Store
    public String specificType;

    @Store
    public int rarity;

    @Store
    public String uniqueID;

    // public abstract BaseBlueprintItemRequest random(BlueprintItemData data);

    public boolean matches(ItemStack stack) {

        if (needsDataItem) {

            ICommonDataItem data = ICommonDataItem.load(stack);

            return data != null && data.Tier() >= this.tier && data.getLevel() >= this.level && data
                    .getSpecificType()
                    .equals(this.specificType) && data.getUniqueGUID()
                    .equals(this.uniqueID);

        } else {

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
        }

        return false;
    }
}
