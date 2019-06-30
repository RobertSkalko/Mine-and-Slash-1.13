package com.robertx22.uncommon.interfaces;

import com.robertx22.uncommon.localization.CLOC;
import net.minecraft.util.text.ITextComponent;

public interface IAutoLocName extends IBaseAutoLoc {

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

}
