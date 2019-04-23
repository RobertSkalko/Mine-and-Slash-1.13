package com.robertx22.uncommon.utilityclasses;

import java.util.List;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class Tooltip {

    public static void add(String str, List<ITextComponent> list) {

	list.add(new TextComponentString(str));
    }
}
