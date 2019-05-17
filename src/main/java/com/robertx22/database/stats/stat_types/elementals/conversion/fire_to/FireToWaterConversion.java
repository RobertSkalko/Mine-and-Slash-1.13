package com.robertx22.database.stats.stat_types.elementals.conversion.fire_to;

import com.robertx22.database.stats.ConversionMethod;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackFireDamage;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stats.stat_types.elementals.conversion.BaseConversionMod;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellWaterDamage;

import java.util.Arrays;
import java.util.List;

public class FireToWaterConversion extends BaseConversionMod {

    @Override
    public String GUID() {
        return "Fire To Water Conversion";
    }

    @Override
    public List<ConversionMethod> conversion() {
        return Arrays.asList(new ConversionMethod(new SpellFireDamage(), new SpellWaterDamage()), new ConversionMethod(new AttackFireDamage(), new AttackWaterDamage()));

    }

}
