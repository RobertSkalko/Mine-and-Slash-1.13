package com.robertx22.mine_and_slash.items.gearitems.baubles;

import com.robertx22.mine_and_slash.a_libraries.curios.interfaces.IRing;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.items.gearitems.bases.BaseBaublesItem;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import net.minecraft.item.Item;

import java.util.HashMap;

public class ItemRing extends BaseBaublesItem implements IRing {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemRing(int rar) {
        super(rar);
    }

    @Override
    public String locNameForLangFile() {
        Rarity rar = Rarities.Items.get(rarity);
        return rar.textFormatColor() + "Ring";
    }

}
