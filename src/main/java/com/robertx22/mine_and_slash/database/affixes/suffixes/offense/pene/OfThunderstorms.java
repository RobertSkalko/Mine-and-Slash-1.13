package com.robertx22.mine_and_slash.database.affixes.suffixes.offense.pene;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class OfThunderstorms extends BaseLegendaryPeneSuffix {

    @Override
    public String GUID() {
        return "Of Thunderstorms";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new ElementalPeneFlat(Elements.Thunder), new ElementalSpellToAttackDMGFlat(Elements.Thunder));

    }

    @Override
    public String locNameForLangFile() {
        return "Of Thunderstorms";
    }

}
