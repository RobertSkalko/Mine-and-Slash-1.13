package com.robertx22.database.stats.stat_types.elementals.conversion.thunder_to;

import com.robertx22.database.stats.ConversionMethod;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.database.stats.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stats.stat_types.elementals.conversion.BaseConversionMod;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.database.stats.stat_types.elementals.spell_damage.SpellWaterDamage;

import java.util.Arrays;
import java.util.List;

public class ThunderToWaterConversion extends BaseConversionMod {

    @Override
    public String GUID() {
        return "Thunder To Water Conversion";
    }

    @Override
    public List<ConversionMethod> conversion() {
        return Arrays.asList(new ConversionMethod(new SpellThunderDamage(), new SpellWaterDamage()), new ConversionMethod(new AttackThunderDamage(), new AttackWaterDamage()));

    }

}
