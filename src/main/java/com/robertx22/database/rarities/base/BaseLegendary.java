package com.robertx22.database.rarities.base;

import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.uncommon.enumclasses.Elements.RGB;
import net.minecraft.util.text.TextFormatting;

public abstract class BaseLegendary implements Rarity {

    @Override
    public String GUID() {

        return "Legendary";
    }

    @Override
    public int Rank() {

        return 4;
    }

    @Override
    public TextFormatting textFormatColor() {
        return TextFormatting.GOLD;
    }

    @Override
    public String Color() {
        return TextFormatting.GOLD.toString();
    }

    @Override
    public RGB getRGBColor() {
        return new RGB(255, 153, 51);
    }

}
