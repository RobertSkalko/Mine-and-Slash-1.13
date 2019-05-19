package com.robertx22.db_lists;

import com.robertx22.database.affixes.Suffix;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Suffixes {

    public static List<Suffix> Weapon = new ArrayList<Suffix>() {
        {
            {
                add(new OfCriticalHits());
                add(new OfCriticalDamage());
                add(new OfCriticalUnity());
                add(new OfVampirism());
                add(new OfForce());
                add(new OfRockPiercing());

            }
        }
    };

    public static List<Suffix> Armor = new ArrayList<Suffix>() {
        {
            {
                add(new OfVitality());
                add(new OfRockSkin());
                add(new OfElementResist());
                add(new OfImmortality());
                add(new OfHiddenSense());
                add(new OfTheDepths());

            }
        }
    };

    public static List<Suffix> Jewerly = new ArrayList<Suffix>() {
        {
            {
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
        list.addAll(Weapon);
        list.addAll(Armor);
        list.addAll(Jewerly);

        for (Suffix s : list) {
            all.put(s.GUID(), s);
        }
    }

    public static HashMap<String, Suffix> All() {

        return all;
    }

}
