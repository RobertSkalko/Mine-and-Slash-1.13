package com.robertx22.database.affixes.prefixes.offense;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.NatureSpellToAttackFlat;

import java.util.Arrays;
import java.util.List;

public class PoisonImbued extends Prefix {

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

}
