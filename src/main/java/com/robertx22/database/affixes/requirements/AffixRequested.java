package com.robertx22.database.affixes.requirements;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.saveclasses.GearItemData;

public class AffixRequested {

    public GearItemSlot forSlot;
    public GearItemData gearData;

    public AffixRequested(GearItemSlot slot, GearItemData data) {
        this.forSlot = slot;
        this.gearData = data;
    }

    public AffixRequested(GearItemData data) {
        this.forSlot = data.GetBaseGearType();
        this.gearData = data;
    }

    public AffixRequested(GearItemSlot slot) {
        this.forSlot = slot;
        this.gearData = new GearItemData();
    }

}
