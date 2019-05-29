package com.robertx22.db_lists;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.affixes.prefixes.defense.Evasive;
import com.robertx22.database.affixes.prefixes.defense.Hardened;
import com.robertx22.database.affixes.prefixes.defense.HeavenlySkin;
import com.robertx22.database.affixes.prefixes.defense.element.PrefixFireRes;
import com.robertx22.database.affixes.prefixes.defense.element.PrefixNatureRes;
import com.robertx22.database.affixes.prefixes.defense.element.PrefixThunderRes;
import com.robertx22.database.affixes.prefixes.defense.element.PrefixWaterRes;
import com.robertx22.database.affixes.prefixes.misc.ThirstOfAcid;
import com.robertx22.database.affixes.prefixes.misc.ThirstOfFlame;
import com.robertx22.database.affixes.prefixes.misc.ThirstOfFrost;
import com.robertx22.database.affixes.prefixes.misc.ThirstOfLightning;
import com.robertx22.database.affixes.prefixes.offense.*;
import com.robertx22.database.affixes.prefixes.offense.damage_percents.*;
import com.robertx22.database.affixes.prefixes.offense.imbued.FlameImbued;
import com.robertx22.database.affixes.prefixes.offense.imbued.FrostImbued;
import com.robertx22.database.affixes.prefixes.offense.imbued.LightningImbued;
import com.robertx22.database.affixes.prefixes.offense.imbued.PoisonImbued;
import com.robertx22.database.affixes.prefixes.resource.Energetic;
import com.robertx22.database.affixes.prefixes.resource.LifeStealing;
import com.robertx22.database.affixes.prefixes.resource.rare_resource.BraveHeart;
import com.robertx22.database.affixes.prefixes.resource.rare_resource.DeepMind;
import com.robertx22.database.affixes.prefixes.resource.rare_resource.InnerSpirit;
import com.robertx22.database.affixes.requirements.AffixRequested;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Prefixes implements IRandom<Prefix, AffixRequested> {

    public static final Prefixes INSTANCE = new Prefixes();

    private static List<Prefix> allPrefixes = new ArrayList<Prefix>() {
        {
            {
                add(new TouchOfMagic());
                add(new Arcanists());
                add(new Magical());

                add(new ThirstOfAcid());
                add(new ThirstOfFrost());
                add(new ThirstOfFlame());
                add(new ThirstOfLightning());

                add(new HardHitting());
                add(new LifeStealing());
                add(new HeavenlyStrikes());

                add(new Flaming());
                add(new Frosty());
                add(new Thorny());
                add(new Thundering());

                add(new Hardened());
                add(new Evasive());
                add(new HeavenlySkin());

                add(new PrefixFireRes());
                add(new PrefixWaterRes());
                add(new PrefixThunderRes());
                add(new PrefixNatureRes());

                add(new BraveHeart());
                add(new DeepMind());
                add(new InnerSpirit());

                add(new Energetic());
                add(new Tough());

                add(new PoisonImbued());
                add(new FlameImbued());
                add(new FrostImbued());
                add(new LightningImbued());

            }
        }
    };
    public static HashMap<String, Prefix> all = new HashMap<>();

    static {
        List<Prefix> list = new ArrayList<Prefix>();
        list.addAll(allPrefixes);

        for (Prefix s : list) {
            all.put(s.GUID(), s);
        }
    }

    @Override
    public Prefix random(AffixRequested affixRequested) {

        List<Prefix> suffixes = allThatMeetRequirement(affixRequested);

        if (suffixes.size() == 0) {
            return null;
        }

        return RandomUtils.weightedRandom(suffixes);
    }

    @Override
    public Prefix random() {
        return RandomUtils.weightedRandom(all.values());
    }

    @Override
    public List<Prefix> allThatMeetRequirement(AffixRequested affixRequested) {
        return all.values()
                .stream()
                .filter(x -> x.requirements().satisfiesAllRequirements(affixRequested))
                .collect(Collectors.toList());
    }
}
