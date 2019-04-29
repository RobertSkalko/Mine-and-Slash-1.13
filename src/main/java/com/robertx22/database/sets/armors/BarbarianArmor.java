package com.robertx22.database.sets.armors;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.LifestealFlat;

import java.util.HashMap;
import java.util.List;

public class BarbarianArmor extends Set {

    @Override
    public String Name() {
        return "Barbarian's Armor";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new LifestealFlat());

                }
            }
        };
    }

    @Override
    public List<GearItemSlot> GearTypes() {
        return this.armor();
    }

}
