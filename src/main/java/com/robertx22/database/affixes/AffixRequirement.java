package com.robertx22.database.affixes;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.db_lists.GearTypes;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AffixRequirement {

    public abstract List<GearItemSlot> slots();

    public boolean onlyOnUniqueItem() {
        return false;
    }

    public int minimumTierIfOnUnique() {
        return 0;
    }

    public boolean meetsRequierment(AffixRequested requested) {

        if (slots().contains(requested.forSlot) == false) {
            return false;
        }

        if (requested.gearData.isUnique() == false && onlyOnUniqueItem()) {
            return false;
        }

        return true;

    }

    static List<GearItemSlot> weaponSlots() {

        return GearTypes.All.values()
                .stream()
                .filter(x -> x.slotType().equals(GearItemSlot.GearSlotType.Weapon))
                .collect(Collectors.toList());

    }

    static List<GearItemSlot> armorSlots() {

        return GearTypes.All.values()
                .stream()
                .filter(x -> x.slotType().equals(GearItemSlot.GearSlotType.Armor))
                .collect(Collectors.toList());

    }

    static List<GearItemSlot> jewerlySlots() {

        return GearTypes.All.values()
                .stream()
                .filter(x -> x.slotType().equals(GearItemSlot.GearSlotType.Jewerly))
                .collect(Collectors.toList());

    }

}
