package com.robertx22.uncommon.utilityclasses;

import com.robertx22.uncommon.localization.CLOC;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class Tooltip {

    public static void add(String str, List<ITextComponent> list) {

        list.add(CLOC.blank(str));
    }

    public static void add(ITextComponent str, List<ITextComponent> list) {
        list.add(str);
    }

}
