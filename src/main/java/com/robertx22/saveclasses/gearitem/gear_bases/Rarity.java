package com.robertx22.saveclasses.gearitem.gear_bases;

import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.interfaces.IWeighted;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public interface Rarity extends IWeighted {

    String GUID();

    int Rank();

    String Color();

    int Weight();

    TextFormatting textFormatColor();

    public default ITextComponent locName() {
        return CLOC.rarity(GUID().toLowerCase());
    }

}
