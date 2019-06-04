package com.robertx22.uncommon.interfaces;

import com.robertx22.uncommon.CLOC;
import net.minecraft.util.text.ITextComponent;

public interface IAutoLocDesc extends IAutoLocName {

    public default String getGroupName() {
        return locDescGroup().name()
                .toUpperCase()
                .replaceAll("_", " ") + " - DESCRIPTIONS";
    }

    public default AutoLocGroup locDescGroup() {
        return this.locNameGroup();
    }

    String locDescLangFileGUID();

    String locDescForLangFile();

    public default ITextComponent locDesc() {
        return CLOC.blank(formatString(formattedLocDescLangFileGUID()));
    }

    public default String formattedLocDescLangFileGUID() {
        return getPrefix() + formatString(locDescLangFileGUID());
    }

}

