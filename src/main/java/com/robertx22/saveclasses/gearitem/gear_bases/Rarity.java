package com.robertx22.saveclasses.gearitem.gear_bases;

import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.interfaces.IWeighted;
import net.minecraft.util.text.ITextComponent;

public interface Rarity extends IWeighted {

    String GUID();

    int Rank();

    String Color();

    int Weight();

    public default ITextComponent locName() {
        return CLOC.rarity(GUID().toLowerCase());
    }

}
