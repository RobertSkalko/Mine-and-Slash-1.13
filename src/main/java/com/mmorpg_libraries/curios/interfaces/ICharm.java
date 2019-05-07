package com.mmorpg_libraries.curios.interfaces;

import com.mmorpg_libraries.curios.CurioSlots;

public interface ICharm extends ICuriosType {

    @Override
    public default String curioTypeName() {
        return CurioSlots.CHARM.name;
    }
}
