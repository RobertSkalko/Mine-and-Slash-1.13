package com.robertx22.database.affixes.prefixes.offense.imbued;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.uncommon.enumclasses.Elements;

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

        return Arrays.asList(new ElementalSpellToAttackDMGFlat(Elements.Nature));
    }

    @Override
    public String locNameForLangFile() {
        return "Poison Imbued";
    }

}
