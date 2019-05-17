package com.robertx22.database.stats.stat_types.elementals.conversion.fire_to;

import com.robertx22.database.stats.ConversionMethod;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackFireDamage;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackNatureDamage;
import com.robertx22.database.stats.stat_types.elementals.conversion.BaseConversionMod;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellNatureDamage;

import java.util.Arrays;
import java.util.List;

public class FireToNatureConversion extends BaseConversionMod {

    @Override
    public String GUID() {
        return "Fire To Nature Conversion";
    }

    @Override
    public List<ConversionMethod> conversion() {
        return Arrays.asList(new ConversionMethod(new SpellFireDamage(), new SpellNatureDamage()), new ConversionMethod(new AttackFireDamage(), new AttackNatureDamage()));

    }

}
