package com.robertx22.db_lists;

import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.affixes.requirements.AffixRequested;
import com.robertx22.database.affixes.suffixes.defense.*;
import com.robertx22.database.affixes.suffixes.offense.*;
import com.robertx22.database.affixes.suffixes.offense.pene.OfFirePene;
import com.robertx22.database.affixes.suffixes.offense.pene.OfNaturePene;
import com.robertx22.database.affixes.suffixes.offense.pene.OfThunderPene;
import com.robertx22.database.affixes.suffixes.offense.pene.OfWaterPene;
import com.robertx22.database.affixes.suffixes.resource.OfManaRegen;
import com.robertx22.database.affixes.suffixes.resource.OfTheDepths;
import com.robertx22.database.affixes.suffixes.resource.OfTheSage;
import com.robertx22.database.affixes.suffixes.resource.OfVampirism;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Suffixes implements IRandom<Suffix, AffixRequested> {

    public static final Suffixes INSTANCE = new Suffixes();

    private static List<Suffix> allSuffixes = new ArrayList<Suffix>() {
        {
            {
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

                add(new OfFirePene());
                add(new OfNaturePene());
                add(new OfThunderPene());
                add(new OfWaterPene());

            }
        }
    };

    public static HashMap<String, Suffix> all = new HashMap<>();

    static {

        List<Suffix> list = new ArrayList<Suffix>();
        list.addAll(allSuffixes);

        for (Suffix s : list) {
            all.put(s.GUID(), s);
        }

    }

    @Override
    public Suffix random(AffixRequested affixRequested) {

        List<Suffix> suffixes = allThatMeetRequirement(affixRequested);

        return RandomUtils.weightedRandom(suffixes);
    }

    @Override
    public Suffix random() {
        return RandomUtils.weightedRandom(all.values());
    }

    @Override
    public List<Suffix> allThatMeetRequirement(AffixRequested affixRequested) {
        return all.values()
                .stream()
                .filter(x -> x.requirements().satisfiesAllRequirements(affixRequested))
                .collect(Collectors.toList());
    }
}
