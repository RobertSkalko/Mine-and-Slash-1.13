package com.robertx22.uncommon.utilityclasses;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackFireDamage;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackNatureDamage;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.HashMap;

public class StatUtils {

    public static HashMap<Elements, Stat> SpellDamage = new HashMap<Elements, Stat>() {
        {
            {
                put(Elements.Nature, new SpellNatureDamage());
                put(Elements.Water, new SpellWaterDamage());
                put(Elements.Thunder, new SpellThunderDamage());
                put(Elements.Fire, new SpellFireDamage());
                put(Elements.None, null);

            }

        }
    };

    public static HashMap<Elements, Stat> AttackDamage = new HashMap<Elements, Stat>() {
        {
            {
                put(Elements.Nature, new AttackNatureDamage());
                put(Elements.Water, new AttackWaterDamage());
                put(Elements.Thunder, new AttackThunderDamage());
                put(Elements.Fire, new AttackFireDamage());
                put(Elements.None, null);

            }

        }
    };

}
