package com.robertx22.database.affixes.requirements;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;

import java.util.ArrayList;
import java.util.List;

public class SlotRequirement extends BaseAffixRequirement {

    public List<GearItemSlot> slots = new ArrayList<>();

    public SlotRequirement(GearItemSlot slot) {
        this.slots.add(slot);
    }

    public SlotRequirement(List<GearItemSlot> slots) {
        this.slots.addAll(slots);
    }

    @Override
    public boolean meetsRequierment(AffixRequested requested) {

        if (slots.contains(requested.forSlot) == false) {
            return false;
        }

        return true;

    }

}
