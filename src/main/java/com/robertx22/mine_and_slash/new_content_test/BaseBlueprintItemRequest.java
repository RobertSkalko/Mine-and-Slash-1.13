package com.robertx22.mine_and_slash.new_content_test;

import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.ItemStack;

@Storable
public class BaseBlueprintItemRequest {

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

        ICommonDataItem data = ICommonDataItem.load(stack);

        return data != null && data.Tier() >= this.tier && data.getLevel() >= this.level && data
                .getSpecificType()
                .equals(this.specificType) && data.getUniqueGUID().equals(this.uniqueID);

    }
}
