package com.robertx22.db_lists;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.affixes.suffixes.OfBalance;
import com.robertx22.database.affixes.suffixes.OfGuidance;
import com.robertx22.database.affixes.suffixes.OfMajorAffinity;
import com.robertx22.database.affixes.suffixes.OfSwiftness;
import com.robertx22.database.affixes.suffixes.defense.*;
import com.robertx22.database.affixes.suffixes.offense.*;
import com.robertx22.database.affixes.suffixes.offense.pene.OfEarthquakes;
import com.robertx22.database.affixes.suffixes.offense.pene.OfFirestorms;
import com.robertx22.database.affixes.suffixes.offense.pene.OfIceStorms;
import com.robertx22.database.affixes.suffixes.offense.pene.OfThunderstorms;
import com.robertx22.database.affixes.suffixes.resource.OfManaRegen;
import com.robertx22.database.affixes.suffixes.resource.OfTheDepths;
import com.robertx22.database.affixes.suffixes.resource.OfTheSage;
import com.robertx22.database.affixes.suffixes.resource.OfVampirism;
import com.robertx22.db_lists.bases.IRandomDefault;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Suffixes implements IRandomDefault<Suffix> {

    public static final Suffixes INSTANCE = new Suffixes();

    private static List<Suffix> allSuffixes = new ArrayList<Suffix>() {
        {
            {

                add(new OfBalance());
                add(new OfGuidance());
                add(new OfSwiftness());

                add(new OfGodhood());
                add(new OfCriticalHits());
                add(new OfCriticalDamage());
                add(new OfCriticalUnity());
                add(new OfVampirism());
                add(new OfForce());
                add(new OfRockPiercing());

                add(new OfVitality());
                add(new OfRockSkin());
                add(new OfElementResist());
                add(new OfImmortality());
                add(new OfHiddenSense());
                add(new OfTheDepths());

                add(new OfVitality());
                add(new OfManaRegen());
                add(new OfTheSage());

                add(new OfFirestorms());
                add(new OfEarthquakes());
                add(new OfThunderstorms());
                add(new OfIceStorms());

            }
        }
    };

    public static HashMap<String, Suffix> all = new HashMap<>();

    private static List<IGenerated<Suffix>> allGenerated = new ArrayList<IGenerated<Suffix>>() {
        {
            {

                add(new OfMajorAffinity(Elements.None));

            }
        }
    };

    static {

        List<Suffix> list = new ArrayList<Suffix>();
        list.addAll(allSuffixes);

        for (Suffix s : list) {
            all.put(s.GUID(), s);
        }

        for (IGenerated<Suffix> gen : allGenerated) {
            for (Suffix statmod : gen.generateAllPossibleStatVariations()) {
                all.put(statmod.GUID(), statmod);
            }
        }

    }

    @Override
    public HashMap<String, Suffix> All() {
        return all;
    }

}
