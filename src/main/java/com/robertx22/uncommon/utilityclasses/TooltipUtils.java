package com.robertx22.uncommon.utilityclasses;

import com.robertx22.uncommon.CLOC;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class TooltipUtils {

    public static TextComponentString level(int lvl) {
        return new TextComponentString(TextFormatting.YELLOW + CLOC.word("level") + ": " + lvl);
    }

}
