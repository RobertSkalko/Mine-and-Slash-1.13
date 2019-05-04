package com.robertx22.uncommon.utilityclasses;

import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.uncommon.CLOC;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class TooltipUtils {

    public static ITextComponent level(int lvl) {
        return new TextComponentString(TextFormatting.YELLOW + "").appendSibling(CLOC.word("level")
                .appendText((": " + lvl)));
    }

    public static ITextComponent rarity(Rarity rarity) {

        return (CLOC.word("rarity")
                .appendText(": ")
                .appendSibling(rarity.locName())
                .setStyle(new Style().setColor(rarity.textFormatColor())));
    }
}
