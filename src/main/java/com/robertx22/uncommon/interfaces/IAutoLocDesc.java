package com.robertx22.uncommon.interfaces;

import net.minecraft.util.text.ITextComponent;

public interface IAutoLocDesc extends IAutoLocName {

    ITextComponent locDesc();

    String locDescLangFileGUID(String guid);

    String locDescForLangFile();

}
