package com.robertx22.database.sets.misc;

import com.robertx22.database.gearitemslots.Charm;
import com.robertx22.database.gearitemslots.Ring;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.EnergyRegenPercent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Limitless extends Set {

    @Override
    public String Name() {
        return "Limitless";
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new EnergyRegenPercent());

                }
            }
        };
    }

    @Override
    public List<GearItemSlot> GearTypes() {
        return Arrays.asList(new Ring(), new Charm());
    }

}