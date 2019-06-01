package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorPeneFlat;
import com.robertx22.database.stats.stat_mods.flat.corestats.StrengthFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.ThunderSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.generated.WeaponDamageFlat;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;

import java.util.Arrays;
import java.util.List;

public class Justice extends BaseMajorArcana {

    public static final String GUID = "Justice";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new StrengthFlat(), new WeaponDamageFlat(WeaponTypes.Sword), new ArmorPeneFlat(), new ThunderSpellToAttackFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Justice";
    }
}
