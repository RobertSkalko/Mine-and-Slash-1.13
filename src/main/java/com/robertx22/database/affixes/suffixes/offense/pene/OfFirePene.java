package com.robertx22.database.affixes.suffixes.offense.pene;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.FireSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.FirePeneFlat;

import java.util.Arrays;
import java.util.List;

public class OfFirePene extends BaseLegendaryPeneSuffix {

    @Override
    public String GUID() {
        return "Of Firestorms";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new FirePeneFlat(), new FireSpellToAttackFlat());

    }

}
