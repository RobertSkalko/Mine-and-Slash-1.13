package com.robertx22.uncommon.interfaces;

import com.robertx22.uncommon.CLOC;
import net.minecraft.util.text.ITextComponent;

public interface IAutoLocDesc extends IAutoLocName {

    String locDescLangFileGUID(String guid);

    String locDescForLangFile();

    public default ITextComponent locDesc() {
        return CLOC.blank(formatString(locDescLangFileGUID(formattedGUID())));
    }

}
