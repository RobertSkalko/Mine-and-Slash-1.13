package com.robertx22.customitems;

import com.robertx22.db_lists.CreativeTabList;

import net.minecraft.item.Item.Properties;

public class ItemDefault extends Properties {

    public ItemDefault() {
	this.maxStackSize(64);
	this.defaultMaxDamage(0);
	this.group(CreativeTabList.MyModTab);
    }
}
