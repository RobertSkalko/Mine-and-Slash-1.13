package com.robertx22.uncommon.utilityclasses;

import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.Words;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class TooltipUtils {

    public static ITextComponent level(int lvl) {
        return new StringTextComponent(TextFormatting.YELLOW + "").appendSibling(Words.Level
                .locName()).appendText((": " + lvl));
    }

    public static ITextComponent rarity(Rarity rarity) {

        return (new StringTextComponent(rarity.textFormatColor() + "").appendSibling(Words.Rarity
                .locName()
                .appendText(": ")
                .appendSibling(rarity.locName())));
    }

    public static ITextComponent tier(int tier) {

        return Styles.YELLOWCOMP()
                .appendSibling(Words.Tier.locName())
                .appendText(": " + tier);

    }

}
