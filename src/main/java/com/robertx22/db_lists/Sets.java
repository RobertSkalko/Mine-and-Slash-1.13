package com.robertx22.db_lists;

import com.robertx22.database.sets.Set;
import com.robertx22.database.sets.armors.ArcheMageVestments;
import com.robertx22.database.sets.armors.BarbarianArmor;
import com.robertx22.database.sets.armors.RangerArmor;
import com.robertx22.database.sets.armors.RockmanChains;
import com.robertx22.database.sets.elemental_jewerly.FlamingDevil;
import com.robertx22.database.sets.elemental_jewerly.ForestGuardian;
import com.robertx22.database.sets.elemental_jewerly.IceLord;
import com.robertx22.database.sets.elemental_jewerly.WillOfLightning;
import com.robertx22.database.sets.misc.Limitless;
import com.robertx22.database.sets.misc.TreeOfLife;

import java.util.HashMap;

public class Sets {
    public static HashMap<String, Set> All = new HashMap<String, Set>() {
        {
            {
                put(new BarbarianArmor().GUID(), new BarbarianArmor());
                put(new ArcheMageVestments().GUID(), new ArcheMageVestments());
                put(new RockmanChains().GUID(), new RockmanChains());
                put(new RangerArmor().GUID(), new RangerArmor());

                put(new IceLord().GUID(), new IceLord());
                put(new FlamingDevil().GUID(), new FlamingDevil());
                put(new ForestGuardian().GUID(), new ForestGuardian());
                put(new WillOfLightning().GUID(), new WillOfLightning());

                put(new TreeOfLife().GUID(), new TreeOfLife());
                put(new Limitless().GUID(), new Limitless());

            }
        }
    };
}
