package com.robertx22.database.affixes.prefixes.offense.imbued;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class FlameImbued extends BaseImbuedPrefix {

    public FlameImbued() {
    }

    @Override
    public String GUID() {
        return "Flame Imbued";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new ElementalSpellToAttackDMGFlat(Elements.Fire));
    }

    @Override
    public String locNameForLangFile() {
        return "Flame Imbued";
    }
}
