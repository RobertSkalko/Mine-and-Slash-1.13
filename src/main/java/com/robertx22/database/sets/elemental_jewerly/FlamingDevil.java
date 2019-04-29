package com.robertx22.database.sets.elemental_jewerly;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.LifestealPercent;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellFireDamagePercent;

import java.util.HashMap;
import java.util.List;

public class FlamingDevil extends Set {

    @Override
    public String Name() {
        return "Flaming Devil";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new LifestealPercent());
                    put(3, new SpellFireDamagePercent());

                }
            }
        };
    }

    @Override
    public List<GearItemSlot> GearTypes() {
        return this.jewerly();
    }

}
