package com.mmorpg_libraries.curios.interfaces;

import com.mmorpg_libraries.curios.CurioSlots;

public interface ISalvageBag extends ICuriosType {
    @Override
    public default String curioTypeName() {
        return CurioSlots.SALVAGE_BAG.name;
    }
}
