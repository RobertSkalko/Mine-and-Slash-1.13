package com.robertx22.database.affixes.suffixes.offense.pene;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.ThunderSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.ThunderPeneFlat;

import java.util.Arrays;
import java.util.List;

public class OfThunderstorms extends BaseLegendaryPeneSuffix {

    @Override
    public String GUID() {
        return "Of Thunderstorms";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new ThunderPeneFlat(), new ThunderSpellToAttackFlat());

    }

    @Override
    public String locNameForLangFile() {
        return "Of Thunderstorms";
    }

}
