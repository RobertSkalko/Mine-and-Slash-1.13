package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.affixes.suffixes.*;
import com.robertx22.mine_and_slash.database.affixes.suffixes.defense.*;
import com.robertx22.mine_and_slash.database.affixes.suffixes.offense.*;
import com.robertx22.mine_and_slash.database.affixes.suffixes.offense.pene.OfEarthquakes;
import com.robertx22.mine_and_slash.database.affixes.suffixes.offense.pene.OfFirestorms;
import com.robertx22.mine_and_slash.database.affixes.suffixes.offense.pene.OfIceStorms;
import com.robertx22.mine_and_slash.database.affixes.suffixes.offense.pene.OfThunderstorms;
import com.robertx22.mine_and_slash.database.affixes.suffixes.resource.OfManaRegen;
import com.robertx22.mine_and_slash.database.affixes.suffixes.resource.OfTheDepths;
import com.robertx22.mine_and_slash.database.affixes.suffixes.resource.OfTheSage;
import com.robertx22.mine_and_slash.database.affixes.suffixes.resource.OfVampirism;
import com.robertx22.mine_and_slash.database.affixes.suffixes.unique.OfGodhood;
import com.robertx22.mine_and_slash.database.affixes.suffixes.unique.OfTheHydra;
import com.robertx22.mine_and_slash.database.affixes.suffixes.unique.OfWeaponFlurry;
import com.robertx22.mine_and_slash.db_lists.bases.IRandomDefault;
import com.robertx22.mine_and_slash.db_lists.newregistry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.newregistry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.newregistry.SlashRegistryType;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Suffixes implements IRandomDefault<Suffix>, ISlashRegistryInit {

    private static final Suffixes INSTANCE = new Suffixes();

    private static List<Suffix> allSuffixes = new ArrayList<Suffix>() {
        {
            {
                add(new OfTheHydra());
                add(new OfGiants());
                add(new OfBehemoths());

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
                add(new OfDissonance(Elements.Physical));
                add(new OfMajorAffinity(Elements.Physical));
                add(new OfWeaponFlurry(WeaponTypes.None));

            }
        }
    };

    public static void init() {

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

    @Override
    public void registerAll() {
        All().values()
                .forEach(x -> SlashRegistry.getRegistry(SlashRegistryType.SUFFIX)
                        .register(x));

    }
}
