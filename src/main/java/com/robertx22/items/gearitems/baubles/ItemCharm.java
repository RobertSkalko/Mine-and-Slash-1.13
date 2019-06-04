package com.robertx22.items.gearitems.baubles;

import com.mmorpg_libraries.curios.interfaces.ICharm;
import com.robertx22.db_lists.Rarities;
import com.robertx22.items.gearitems.bases.BaseBaublesItem;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import net.minecraft.item.Item;

import java.util.HashMap;

public class ItemCharm extends BaseBaublesItem implements ICharm {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemCharm(int rar) {
        super(rar);
    }

    @Override
    public String locNameForLangFile() {
        Rarity rar = Rarities.Items.get(rarity);
        return rar.textFormatColor() + "Charm";
    }

}
