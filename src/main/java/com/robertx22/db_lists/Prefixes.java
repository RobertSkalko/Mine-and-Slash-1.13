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
import com.robertx22.database.affixes.prefixes.resource.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Prefixes {

    public static List<Prefix> Weapon = new ArrayList<Prefix>() {
        {
            {

                add(new ThirstOfAcid());
                add(new ThirstOfFrost());
                add(new ThirstOfFlame());
                add(new ThirstOfLightning());

                add(new HardHitting());
                add(new LifeStealing());
                add(new HeavenlyStrikes());

            }
        }
    };

    public static List<Prefix> Armor = new ArrayList<Prefix>() {
        {
            {

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

            }
        }
    };

    public static List<Prefix> Jewerly = new ArrayList<Prefix>() {
        {
            {

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

    public static HashMap<String, Prefix> All() {

        if (all.isEmpty()) {

            List<Prefix> list = new ArrayList<Prefix>();
            list.addAll(Weapon);
            list.addAll(Armor);
            list.addAll(Jewerly);

            HashMap<String, Prefix> map = new HashMap<String, Prefix>();

            for (Prefix s : list) {
                map.put(s.GUID(), s);
            }
            all = map;

        }

        return all;
    }

}
