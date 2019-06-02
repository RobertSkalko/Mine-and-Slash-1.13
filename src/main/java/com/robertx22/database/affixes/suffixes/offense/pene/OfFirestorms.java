package com.robertx22.database.affixes.suffixes.offense.pene;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.generated.ElementalPeneFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class OfFirestorms extends BaseLegendaryPeneSuffix {

    @Override
    public String GUID() {
        return "Of Firestorms";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new ElementalPeneFlat(Elements.Fire), new ElementalSpellToAttackDMGFlat(Elements.Fire));

    }

    @Override
    public String locNameForLangFile() {
        return "Of Firestorms";
    }
}
