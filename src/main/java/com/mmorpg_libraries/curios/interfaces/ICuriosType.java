package com.mmorpg_libraries.curios.interfaces;

public interface ICuriosType {

    String curioTypeName();

    default boolean rightClickEquip() {
        return true;
    }

}