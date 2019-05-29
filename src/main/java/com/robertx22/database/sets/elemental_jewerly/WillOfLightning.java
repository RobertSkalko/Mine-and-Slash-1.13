package com.robertx22.database.sets.elemental_jewerly;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.offense.CriticalHitPercent;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellThunderDamagePercent;

import java.util.HashMap;
import java.util.List;

public class WillOfLightning extends Set {

    @Override
    public String Name() {
        return "Will of Lightning";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new CriticalHitPercent());
                    put(3, new SpellThunderDamagePercent());

                }
            }
        };
    }

    @Override
    public List<GearItemSlot> GearTypes() {
        return this.jewerly();
    }

}
