package com.robertx22.database.affixes.prefixes.offense.imbued;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.WaterSpellToAttackFlat;

import java.util.Arrays;
import java.util.List;

public class FrostImbued extends BaseImbuedPrefix {

    public FrostImbued() {
    }

    @Override
    public String GUID() {
        return "Frost Imbued";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new WaterSpellToAttackFlat());
    }

}
