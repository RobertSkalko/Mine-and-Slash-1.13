package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.WisdomFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthPercent;
import com.robertx22.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.database.stats.stat_mods.generated.WeaponDamageFlat;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class TheEmperor extends BaseMajorArcana {

    public static final String GUID = "TheEmperor";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new WisdomFlat(), new HealthPercent(), new WeaponDamageFlat(WeaponTypes.Staff), new ElementalSpellToAttackDMGFlat(Elements.Fire));
    }

    @Override
    public String locNameForLangFile() {
        return "The Emperor";
    }
}
