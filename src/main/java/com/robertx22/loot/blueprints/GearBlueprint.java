package com.robertx22.loot.blueprints;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.rarities.RaritiesContainer;
import com.robertx22.database.rarities.containers.ItemRarities;
import com.robertx22.database.requirements.GearRequestedFor;
import com.robertx22.database.sets.Set;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.db_lists.Sets;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.SetData;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class GearBlueprint extends ItemBlueprint {

    public GearBlueprint(int level) {
        super(level);
    }

    public String gearType = "";
    public boolean RandomGearType = true;
    public float chaosStatChance = 1;

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {

        return new ItemRarities();

    }

    public boolean getsChaosStats() {
        return RandomUtils.roll(chaosStatChance);
    }

    public void SetSpecificType(String type) {

        gearType = type;
        RandomGearType = false;

        try {
            GearTypes.All.get(type);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    public GearItemSlot GetGearType() {

        if (RandomGearType) {

            return RandomUtils.weightedRandom(GearTypes.All.values());

        } else {

            return GearTypes.All.get(gearType);
        }

    }

    public boolean isCustomSetChance = false;
    public float customSetChance = 0;

    public void SetCustomSetChance(float chance) {
        isCustomSetChance = true;
        customSetChance = chance;
    }

    public boolean canGetSet(GearItemData data) {

        Set set = Sets.INTANCE.random(new GearRequestedFor(data));

        if (set == null) {
            return false;
        } else {
            if (this.isCustomSetChance) {

                if (RandomUtils.roll(this.customSetChance)) {
                    return true;
                }

            } else {
                if (RandomUtils.roll(data.GetRarity().SetChance())) {
                    return true;
                }
            }

            return false;
        }
    }

    public SetData generateSet(GearItemData data) {

        SetData setdata = null;

        Set set = Sets.INTANCE.random(new GearRequestedFor(data));

        if (set != null) {

            setdata = new SetData();
            setdata.baseSet = set.GUID();
        }

        return setdata;
    }

}
