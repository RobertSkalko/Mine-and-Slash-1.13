package com.robertx22.uncommon.utilityclasses;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.List;

public class Tooltip {

    public static void add(String str, List<ITextComponent> list) {

        list.add(new TextComponentTranslation(str));
    }

    public static void add(ITextComponent str, List<ITextComponent> list) {

        list.add(str);
    }

}
