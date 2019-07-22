package com.robertx22.mine_and_slash.new_content_test.requests;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.DataItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

@Storable
public class BlueprintDataItemRequest extends BaseBlueprintRequest {

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

    @Store
    public DataItemType dataItemType;

    // public abstract BaseBlueprintItemRequest random(BlueprintItemData data);

    @Override
    public boolean matches(ItemStack stack) {

        ICommonDataItem data = ICommonDataItem.load(stack);

        if (data.getDataType() == dataItemType) {
            return data != null && data.Tier() >= this.tier && data.getLevel() >= this.level && data
                    .getSpecificType()
                    .equals(this.specificType) && data.getUniqueGUID()
                    .equals(this.uniqueID);
        }

        return false;
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        return list;
    }
}
