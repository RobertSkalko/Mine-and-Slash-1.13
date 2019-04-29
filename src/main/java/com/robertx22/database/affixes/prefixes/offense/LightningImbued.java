package com.robertx22.database.affixes.prefixes.offense;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.ThunderSpellToAttackFlat;

import java.util.Arrays;
import java.util.List;

public class LightningImbued extends Prefix {

    public LightningImbued() {
    }

    @Override
    public String GUID() {
        return "Lightning Imbued";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new ThunderSpellToAttackFlat());
    }

}
