package com.robertx22.database.sets.armors;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.weapon_damages.BowDamageFlat;
import com.robertx22.saveclasses.gearitem.gear_bases.Set;

import java.util.HashMap;
import java.util.List;

public class RangerArmor extends Set {

    @Override
    public String Name() {
        return "Ranger's Armor";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new CriticalDamageFlat());
                    put(3, new BowDamageFlat());

                }
            }
        };
    }

    @Override
    public List<GearItemSlot> GearTypes() {
        return this.armor();
    }

}
