package com.mmorpg_libraries.curios;

public enum CurioSlots {

    CHARM(RefCurio.CHARM), NECKLACE(RefCurio.NECKLACE), RING(RefCurio.RING), BRACELET(RefCurio.BRACELET), SALVAGE_BAG(RefCurio.SALVAGE_BAG);

    public String name;

    CurioSlots(String name) {
        this.name = name;
    }

}


