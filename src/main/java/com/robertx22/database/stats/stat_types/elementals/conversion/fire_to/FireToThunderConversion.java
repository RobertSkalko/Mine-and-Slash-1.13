package com.robertx22.database.stats.stat_types.elementals.conversion.fire_to;

import com.robertx22.database.stats.ConversionMethod;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackFireDamage;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.database.stats.stat_types.elementals.conversion.BaseConversionMod;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellThunderDamage;

import java.util.Arrays;
import java.util.List;

public class FireToThunderConversion extends BaseConversionMod {

    @Override
    public String GUID() {
        return "Fire To Thunder Conversion";
    }

    @Override
    public List<ConversionMethod> conversion() {
        return Arrays.asList(new ConversionMethod(new SpellFireDamage(), new SpellThunderDamage()), new ConversionMethod(new AttackFireDamage(), new AttackThunderDamage()));

    }
}
