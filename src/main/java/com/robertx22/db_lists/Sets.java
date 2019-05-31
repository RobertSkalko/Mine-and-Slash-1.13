package com.robertx22.db_lists;

import com.robertx22.database.sets.Set;
import com.robertx22.database.sets.from_lvl_50.GodKingsPlate;
import com.robertx22.database.sets.from_lvl_50.TheAscended;
import com.robertx22.database.sets.low_lvl.armors.BarbarianArmor;
import com.robertx22.database.sets.low_lvl.armors.MagesRobes;
import com.robertx22.database.sets.low_lvl.armors.RangerArmor;
import com.robertx22.database.sets.low_lvl.armors.RockmanChains;
import com.robertx22.database.sets.low_lvl.elemental_jewerly.FlamingDevil;
import com.robertx22.database.sets.low_lvl.elemental_jewerly.ForestGuardian;
import com.robertx22.database.sets.low_lvl.elemental_jewerly.IceLord;
import com.robertx22.database.sets.low_lvl.elemental_jewerly.WillOfLightning;
import com.robertx22.database.sets.low_lvl.misc.Limitless;
import com.robertx22.database.sets.low_lvl.misc.TreeOfLife;
import com.robertx22.database.sets.mid_lvl.ArmorOfTheElements;
import com.robertx22.database.sets.mid_lvl.ArtifactArmor;
import com.robertx22.database.sets.mid_lvl.SpiritOfTheArcane;
import com.robertx22.database.sets.mid_lvl.TheProtector;
import com.robertx22.db_lists.bases.IRandomDefault;

import java.util.HashMap;

public class Sets implements IRandomDefault<Set> {

    public static final Sets INTANCE = new Sets();

    public static HashMap<String, Set> All = new HashMap<String, Set>() {
        {
            {
                //high lvl
                put(new GodKingsPlate().GUID(), new GodKingsPlate());
                put(new TheAscended().GUID(), new TheAscended());

                // mid lvl
                put(new ArmorOfTheElements().GUID(), new ArmorOfTheElements());
                put(new TheProtector().GUID(), new TheProtector());
                put(new SpiritOfTheArcane().GUID(), new SpiritOfTheArcane());
                put(new ArtifactArmor().GUID(), new ArtifactArmor());

                // low lvl
                put(new BarbarianArmor().GUID(), new BarbarianArmor());
                put(new MagesRobes().GUID(), new MagesRobes());
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

    @Override
    public HashMap<String, Set> All() {
        return All;
    }

}
