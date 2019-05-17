package com.robertx22.database.stats.stat_types.elementals.conversion.nature_to;

import com.robertx22.database.stats.ConversionMethod;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackNatureDamage;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.database.stats.stat_types.elementals.conversion.BaseConversionMod;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellThunderDamage;

import java.util.Arrays;
import java.util.List;

public class NatureToThunderConversion extends BaseConversionMod {

    @Override
    public String GUID() {
        return "Nature To Thunder Conversion";
    }

    @Override
    public List<ConversionMethod> conversion() {
        return Arrays.asList(new ConversionMethod(new SpellNatureDamage(), new SpellThunderDamage()), new ConversionMethod(new AttackNatureDamage(), new AttackThunderDamage()));

    }

}
