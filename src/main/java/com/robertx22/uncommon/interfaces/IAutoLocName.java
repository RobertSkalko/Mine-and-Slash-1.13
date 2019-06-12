package com.robertx22.uncommon.interfaces;

import com.robertx22.uncommon.CLOC;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.text.ITextComponent;

public interface IAutoLocName extends IBaseAutoLoc {

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
        Currency_Items

    }

    public default String getGroupName() {
        return locNameGroup().name().toUpperCase().replaceAll("_", " ") + " - NAMES";
    }

    AutoLocGroup locNameGroup();

    String locNameLangFileGUID();

    public default String formattedLocNameLangFileGUID() {
        return getPrefix() + formatString(locNameLangFileGUID());
    }

    public default String translate() {
        return CLOC.translate(this.locName());
    }

    public String locNameForLangFile();

    public default ITextComponent locName() {
        return CLOC.blank(formatString(locNameLangFileGUID()));
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
