package com.robertx22.database.affixes.prefixes.offense.imbued;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.NatureSpellToAttackFlat;

import java.util.Arrays;
import java.util.List;

public class PoisonImbued extends BaseImbuedPrefix {

    public PoisonImbued() {
    }

    @Override
    public String GUID() {
        return "Poison Imbued";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new NatureSpellToAttackFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Poison Imbued";
    }

}
