package com.robertx22.database.rarities.base;

import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.uncommon.enumclasses.Elements.RGB;
import net.minecraft.util.text.TextFormatting;

public abstract class BaseEpic implements Rarity {

    @Override
    public String GUID() {

        return "Epic";
    }

    @Override
    public int Rank() {

        return 3;
    }

    @Override
    public TextFormatting textFormatColor() {
        return TextFormatting.BLUE;
    }

    @Override
    public String Color() {
        return TextFormatting.BLUE.toString();
    }

    @Override
    public RGB getRGBColor() {
        return new RGB(51, 102, 255);
    }

    @Override
    public String locNameForLangFile() {
        return "Epic";
    }
}