package com.robertx22.database.requirements;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.db_lists.GearTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SlotRequirement extends BaseRequirement {

    public List<GearItemSlot> slots = new ArrayList<>();

    public SlotRequirement(GearItemSlot slot) {
        this.slots.add(slot);
    }

    public SlotRequirement(List<GearItemSlot> slots) {
        this.slots.addAll(slots);
    }

    @Override
    public boolean meetsRequierment(GearRequestedFor requested) {

        if (slots.contains(requested.forSlot) == false) {
            return false;
        }

        return true;

    }

    public static SlotRequirement weaponsOnly() {
        return new SlotRequirement(weaponSlots());
    }

    public static SlotRequirement armorsOnly() {
        return new SlotRequirement(armorSlots());
    }

    public static SlotRequirement armorsOnlyNoOffHand() {
        return new SlotRequirement(armorSlotsNoOffHand());
    }

    public static SlotRequirement jewerlyOnly() {
        return new SlotRequirement(jewerlySlots());
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
                .filter(x -> x.slotType()
                        .equals(GearItemSlot.GearSlotType.Armor) || x.slotType()
                        .equals(GearItemSlot.GearSlotType.OffHand)) // tenp i dont have enough affixes for off hands
                .collect(Collectors.toList());

    }

    static List<GearItemSlot> armorSlotsNoOffHand() {

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
