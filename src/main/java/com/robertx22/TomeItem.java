package com.robertx22;

import com.robertx22.db_lists.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class TomeItem extends Item {

    public TomeItem(Properties prop, int rarity) {
        super(prop.group(CreativeTabs.LootBoxes));
        this.rarity = rarity;
    }

    public int rarity;
    public ResourceLocation texture = new ResourceLocation("textures/entity/enchanting_table_book.png");

}
