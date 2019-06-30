package com.robertx22.uncommon.localization;

import com.robertx22.mmorpg.MMORPG;
import com.robertx22.mmorpg.Ref;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.TextFormatting;

public class CLOC {

    public static String translate(ITextComponent s) {
        return MMORPG.proxy.translate(s);
    }

    private static ITextComponent base(String s) {
        return new TranslationTextComponent(s);
    }

    public static ITextComponent tooltip(String str) {

        return base(Ref.MODID + ".tooltip." + str);

    }

    public static ITextComponent lore(String str) {

        return new StringTextComponent(TextFormatting.GREEN + "'").appendSibling(base(Ref.MODID + ".lore." + str)
                .appendSibling(new StringTextComponent("'")));

    }

    public static ITextComponent blank(String string) {
        return base(string);
    }

}
