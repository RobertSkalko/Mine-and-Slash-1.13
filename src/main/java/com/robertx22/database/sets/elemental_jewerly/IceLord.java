package com.robertx22.database.sets.elemental_jewerly;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.ManaRegenPercent;
import com.robertx22.database.stats.stat_mods.percent.spell_ele_dmg.SpellWaterDamagePercent;

import java.util.HashMap;
import java.util.List;

public class IceLord extends Set {

    @Override
    public String Name() {
        return "Ice Lord";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new ManaRegenPercent());
                    put(3, new SpellWaterDamagePercent());

                }
            }
        };
    }

    @Override
    public List<GearItemSlot> GearTypes() {
        return this.jewerly();
    }

}
