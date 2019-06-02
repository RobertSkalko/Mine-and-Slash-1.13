package com.robertx22.db_lists;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.affixes.prefixes.defense.Evasive;
import com.robertx22.database.affixes.prefixes.defense.Gatekeepers;
import com.robertx22.database.affixes.prefixes.defense.Hardened;
import com.robertx22.database.affixes.prefixes.defense.HeavenlySkin;
import com.robertx22.database.affixes.prefixes.defense.element.PrefixFireRes;
import com.robertx22.database.affixes.prefixes.defense.element.PrefixNatureRes;
import com.robertx22.database.affixes.prefixes.defense.element.PrefixThunderRes;
import com.robertx22.database.affixes.prefixes.defense.element.PrefixWaterRes;
import com.robertx22.database.affixes.prefixes.misc.*;
import com.robertx22.database.affixes.prefixes.offense.*;
import com.robertx22.database.affixes.prefixes.offense.damage_percents.*;
import com.robertx22.database.affixes.prefixes.offense.imbued.FlameImbued;
import com.robertx22.database.affixes.prefixes.offense.imbued.FrostImbued;
import com.robertx22.database.affixes.prefixes.offense.imbued.LightningImbued;
import com.robertx22.database.affixes.prefixes.offense.imbued.PoisonImbued;
import com.robertx22.database.affixes.prefixes.resource.Energetic;
import com.robertx22.database.affixes.prefixes.resource.LifeStealing;
import com.robertx22.database.affixes.prefixes.resource.Tenacious;
import com.robertx22.database.affixes.prefixes.resource.Wise;
import com.robertx22.database.affixes.prefixes.resource.rare_resource.BraveHeart;
import com.robertx22.database.affixes.prefixes.resource.rare_resource.DeepMind;
import com.robertx22.database.affixes.prefixes.resource.rare_resource.InnerSpirit;
import com.robertx22.db_lists.bases.IRandomDefault;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Prefixes implements IRandomDefault<Prefix> {

    public static final Prefixes INSTANCE = new Prefixes();

    private static List<Prefix> allPrefixes = new ArrayList<Prefix>() {
        {
            {
                add(new Looters());
                add(new Archaeologists());
                add(new TreasureSeeker());

                add(new Wise());
                add(new Tenacious());

                add(new Gatekeepers());

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

    private static List<IGenerated<Prefix>> allGenerated = new ArrayList<IGenerated<Prefix>>() {
        {
            {

            }
        }
    };

    static {
        List<Prefix> list = new ArrayList<Prefix>();
        list.addAll(allPrefixes);

        for (Prefix s : list) {
            all.put(s.GUID(), s);
        }

        for (IGenerated<Prefix> gen : allGenerated) {
            for (Prefix statmod : gen.generateAllPossibleStatVariations()) {
                all.put(statmod.GUID(), statmod);
            }
        }

    }

    @Override
    public HashMap<String, Prefix> All() {
        return all;
    }

}
