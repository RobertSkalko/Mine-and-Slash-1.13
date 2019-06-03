package com.robertx22.uncommon.utilityclasses;

import com.robertx22.Words;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class TooltipUtils {

    public static ITextComponent level(int lvl) {
        return new TextComponentString(TextFormatting.YELLOW + "").appendSibling(Words.Level
                .locName()).appendText((": " + lvl));
    }

    public static ITextComponent rarity(Rarity rarity) {

        return (new TextComponentString(rarity.textFormatColor() + "").appendSibling(Words.Rarity
                .locName()
                .appendText(": ")
                .appendSibling(rarity.locName())));
    }
}
