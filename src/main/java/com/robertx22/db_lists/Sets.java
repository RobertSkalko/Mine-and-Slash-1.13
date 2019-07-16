package com.robertx22.db_lists;

import com.robertx22.database.sets.Set;
import com.robertx22.database.sets.endgame_lvl.AscensionOfElement;
import com.robertx22.database.sets.endgame_lvl.RingsOfImpossibility;
import com.robertx22.database.sets.from_lvl_50.*;
import com.robertx22.database.sets.low_lvl.armors.*;
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
import com.robertx22.database.sets.uniques.TreasureFinder;
import com.robertx22.database.sets.uniques.WisdomOfTheElders;
import com.robertx22.db_lists.bases.IRandomDefault;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sets implements IRandomDefault<Set> {

    public static final Sets INTANCE = new Sets();

    public static HashMap<String, Set> All = new HashMap<String, Set>();

    private static List<Set> generated = new ArrayList<Set>() {
        {
            {

                add(new AscensionOfElement(Elements.Physical));
                add(new RoyalThiefAdornments());
                add(new RingsOfImpossibility());

                add(new Eliminator());
                add(new TreasureFinder());
                //high lvl
                add(new MysticalOrnaments());
                add(new ElementalEssence());
                add(new SeersGuidance());
                add(new GodKingsPlate());
                add(new TheAscended());

                // mid lvl
                add(new ArmorOfTheElements());
                add(new TheProtector());
                add(new SpiritOfTheArcane());
                add(new ArtifactArmor());

                // low lvl
                add(new BarbarianArmor());
                add(new MagesRobes());
                add(new RockmanChains());
                add(new RangerArmor());

                add(new IceLord());
                add(new FlamingDevil());
                add(new ForestGuardian());
                add(new WillOfLightning());

                add(new TreeOfLife());
                add(new Limitless());
                add(new ScholarRobes());

                add(new WisdomOfTheElders());

            }
        }
    };

    @Override
    public HashMap<String, Set> All() {
        return All;
    }

    public static void init() {

        for (Set set : generated) {
            if (set instanceof IGenerated) {
                for (Set statmod : ((IGenerated<Set>) set).generateAllPossibleStatVariations()) {
                    All.put(statmod.GUID(), statmod);
                }
            } else {
                All.put(set.GUID(), set);
            }

        }
    }

}
