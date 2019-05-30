package com.robertx22.uncommon;

import com.robertx22.mmorpg.MMORPG;
import com.robertx22.mmorpg.Ref;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class CLOC {

    public static String translate(ITextComponent s) {
        return MMORPG.proxy.translate(s);
    }

    private static ITextComponent base(String s) {
        return new TextComponentTranslation(s);
    }

    public static ITextComponent tooltip(String str) {

        return base(Ref.MODID + ".tooltip." + str);

    }

    public static ITextComponent geartype(String str) {

        return base(Ref.MODID + ".gear_type." + str);
    }

    public static ITextComponent word(String str) {

        return base(Ref.MODID + ".word." + str);

    }

    public static ITextComponent rarity(String str) {

        return base(Ref.MODID + ".rarity." + str);

    }

    public static ITextComponent lore(String str) {

        return new TextComponentString(TextFormatting.GREEN + "'").appendSibling(base(Ref.MODID + ".lore." + str)
                .appendSibling(new TextComponentString("'")));

    }

    public static ITextComponent blank(String string) {

        return base(string);
    }

    public static ITextComponent set(String guid) {
        return base(Ref.MODID + ".set." + guid);
    }

    public static ITextComponent statDesc(String guidFormatted) {
        return base(Ref.MODID + ".stat_desc." + guidFormatted);
    }
}
