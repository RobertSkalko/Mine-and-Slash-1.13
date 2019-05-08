package com.mmorpg_libraries.curios.interfaces;

import com.mmorpg_libraries.curios.CurioSlots;

public interface INecklace extends ICuriosType {

    @Override
    public default String curioTypeName() {
        return CurioSlots.NECKLACE.name;
    }

}
