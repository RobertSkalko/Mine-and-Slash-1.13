package com.robertx22;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class Styles {

    public static TextFormatting BLUE = TextFormatting.BLUE;
    public static TextFormatting GOLD = TextFormatting.GOLD;
    public static TextFormatting GREEN = TextFormatting.GREEN;
    public static TextFormatting YELLOW = TextFormatting.YELLOW;
    public static TextFormatting RED = TextFormatting.RED;
    public static TextFormatting DARK_GREEN = TextFormatting.DARK_GREEN;
    public static TextFormatting GRAY = TextFormatting.GRAY;
    public static TextFormatting LIGHT_PURPLE = TextFormatting.LIGHT_PURPLE;
    public static TextFormatting AQUA = TextFormatting.AQUA;

    public static ITextComponent BLUECOMP() {
        return new TextComponentString(BLUE + "");
    }

    public static ITextComponent GOLDCOMP() {
        return new TextComponentString(GOLD + "");
    }

    public static ITextComponent GREENCOMP() {
        return new TextComponentString(GREEN + "");
    }

    public static ITextComponent YELLOWCOMP() {
        return new TextComponentString(YELLOW + "");
    }

    public static ITextComponent REDCOMP() {
        return new TextComponentString(RED + "");
    }

    public static ITextComponent DARK_GREENCOMP() {
        return new TextComponentString(DARK_GREEN + "");
    }

    public static ITextComponent GRAYCOMP() {
        return new TextComponentString(GRAY + "");
    }

    public static ITextComponent LIGHT_PURPLECOMP() {
        return new TextComponentString(LIGHT_PURPLE + "");
    }

    public static ITextComponent AQUACOMP() {
        return new TextComponentString(AQUA + "");
    }

}


