package com.mmorpg_libraries.curios;

import com.mmorpg_libraries.curios.interfaces.*;

public enum CurioSlots {

    CHARM(RefCurio.CHARM), NECKLACE(RefCurio.NECKLACE), RING(RefCurio.RING), BRACELET(RefCurio.BRACELET), SALVAGE_BAG(RefCurio.SALVAGE_BAG);

    public String name;

    public static CurioSlots getSlotByClass(ICuriosType type) {

        if (type instanceof IRing) {
            return RING;
        } else if (type instanceof IBracelet) {
            return BRACELET;
        } else if (type instanceof ICharm) {
            return CHARM;
        } else if (type instanceof INecklace) {
            return NECKLACE;
        } else if (type instanceof ISalvageBag) {
            return SALVAGE_BAG;
        }

        return null;

    }

    CurioSlots(String name) {
        this.name = name;
    }

}


