package com.robertx22.uncommon.interfaces;

import com.robertx22.uncommon.CLOC;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public interface IAutoLocMultiLore extends IAutoLocName {

    public default String getGroupName() {
        return locNameGroup().name().toUpperCase().replaceAll("_", " ") + " - LORE LINES";
    }

    List<String> loreLines();

    default List<ITextComponent> getComponents() {
        List<ITextComponent> list = new ArrayList<>();
        for (String prefix : getPrefixListForLangFile()) {
            list.add(CLOC.blank(prefix + this.formattedGUID()));
        }
        return list;
    }

    default List<String> getPrefixListForLangFile() {

        List<String> list = new ArrayList<>();

        int i = 0;
        for (String line : loreLines()) {
            list.add("lore_" + i++ + "_");
        }

        return list;

    }
}
