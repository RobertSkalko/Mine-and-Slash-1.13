package com.robertx22.uncommon.interfaces;

import com.robertx22.uncommon.CLOC;
import net.minecraft.util.text.ITextComponent;

public interface IAutoLocName extends ILocName {

    String locNameLangFileGUID(String guid);

    public default String translate() {
        return CLOC.translate(this.locName());
    }

    public String locNameForLangFile();

    public default ITextComponent locName() {
        return CLOC.blank(locNameLangFileGUID(formattedGUID()));
    }

}
