package com.robertx22.database.affixes.prefixes.offense;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.FireSpellToAttackFlat;

import java.util.Arrays;
import java.util.List;

public class FlameImbued extends Prefix {

    public FlameImbued() {
    }

    @Override
    public String GUID() {
        return "Flame Imbued";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new FireSpellToAttackFlat());
    }

}
