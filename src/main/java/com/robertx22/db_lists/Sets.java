package com.robertx22.db_lists;

import com.robertx22.database.sets.Set;
import com.robertx22.database.sets.endgame_lvl.AscensionOfElement;
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

    public static HashMap<String, Set> All = new HashMap<String, Set>() {
        {
            {
                put(new TreasureFinder().GUID(), new TreasureFinder());

                //high lvl
                put(new MysticalOrnaments().GUID(), new MysticalOrnaments());
                put(new ElementalEssence().GUID(), new ElementalEssence());
                put(new SeersGuidance().GUID(), new SeersGuidance());
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
                put(new ScholarRobes().GUID(), new ScholarRobes());

                put(new WisdomOfTheElders().GUID(), new WisdomOfTheElders());

            }
        }
    };
    private static List<IGenerated<Set>> generated = new ArrayList<IGenerated<Set>>() {
        {
            {
                add(new AscensionOfElement(Elements.None));

            }
        }
    };

    @Override
    public HashMap<String, Set> All() {
        return All;
    }

    public static void init() {
        for (IGenerated<Set> gen : generated) {
            for (Set statmod : gen.generateAllPossibleStatVariations()) {
                All.put(statmod.GUID(), statmod);
            }

        }
    }

}
