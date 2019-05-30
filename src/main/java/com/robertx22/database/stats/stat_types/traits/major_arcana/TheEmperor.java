package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.WisdomFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.FireSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.flat.weapon_damages.StaffDamageFlat;

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
        return Arrays.asList(new WisdomFlat(), new HealthFlat(), new StaffDamageFlat(), new FireSpellToAttackFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "The Emperor";
    }
}
