package com.robertx22.items.gearitems.baubles;

import com.mmorpg_libraries.curios.interfaces.INecklace;
import com.robertx22.items.gearitems.bases.BaseBaublesItem;
import net.minecraft.item.Item;

import java.util.HashMap;

public class ItemNecklace extends BaseBaublesItem implements INecklace {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemNecklace() {
    }

}
