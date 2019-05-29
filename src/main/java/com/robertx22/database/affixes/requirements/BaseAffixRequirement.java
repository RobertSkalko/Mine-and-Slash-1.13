package com.robertx22.database.affixes.requirements;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.db_lists.GearTypes;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseAffixRequirement {

    public abstract boolean meetsRequierment(AffixRequested requested);

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
