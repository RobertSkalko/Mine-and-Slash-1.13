package com.robertx22.uncommon.interfaces;

import com.robertx22.database.IGUID;
import net.minecraft.util.text.ITextComponent;

public interface IAutoLocDesc extends IGUID {

    ITextComponent locDesc();

    String locDescLangFileGUID(String guid);

    String locDescForLangFile();
}
