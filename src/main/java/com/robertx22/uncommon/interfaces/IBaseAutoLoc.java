package com.robertx22.uncommon.interfaces;

import com.robertx22.database.IGUID;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public interface IBaseAutoLoc extends IGUID {

    enum AutoLocGroup {
        Runes,
        Unique_Items,
        Spells,
        Gear_Items,
        Words,
        Rarities,
        Prefixes,
        Suffixes,
        Rune_Words,
        Item_Sets,
        Stats,
        Misc,
        Gear_Slots,
        World_Types,
        Lootboxes,
        Chat_Messages,
        Configs,
        Currency_Items,
        Advancement_titles,
        Advancement_descriptions,

    }

    default String getPrefix() {

        if (this instanceof Item) {
            return "item.";
        } else if (this instanceof Block) {
            return "block.";
        } else {
            return "";
        }

    }

}
