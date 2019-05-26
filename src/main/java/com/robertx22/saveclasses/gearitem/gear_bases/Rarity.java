package com.robertx22.saveclasses.gearitem.gear_bases;

import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.enumclasses.IColor;
import com.robertx22.uncommon.interfaces.ILocName;
import com.robertx22.uncommon.interfaces.IWeighted;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public interface Rarity extends IWeighted, ILocName, IColor {

    String GUID();

    int Rank();

    String Color();

    int Weight();

    TextFormatting textFormatColor();

    @Override
    public default ITextComponent locName() {
        return CLOC.rarity(GUID().toLowerCase());
    }

}
