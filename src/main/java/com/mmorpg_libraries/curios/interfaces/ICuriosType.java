package com.mmorpg_libraries.curios.interfaces;

import com.robertx22.uncommon.interfaces.IGearItem;

public interface ICuriosType extends IGearItem {

    String curioTypeName();

    default boolean rightClickEquip() {
        return true;
    }

}
