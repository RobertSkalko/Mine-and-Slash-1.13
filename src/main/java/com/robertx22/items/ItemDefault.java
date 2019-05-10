package com.robertx22.items;

import com.robertx22.db_lists.CreativeTabs;
import net.minecraft.item.Item.Properties;

public class ItemDefault extends Properties {

    public ItemDefault() {
        this.maxStackSize(64);
        this.defaultMaxDamage(0);
        this.group(CreativeTabs.MyModTab);

    }
}
