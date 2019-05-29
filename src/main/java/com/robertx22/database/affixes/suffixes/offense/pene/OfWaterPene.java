package com.robertx22.database.affixes.suffixes.offense.pene;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.bonus.WaterSpellToAttackFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.WaterPeneFlat;

import java.util.Arrays;
import java.util.List;

public class OfWaterPene extends BaseLegendaryPeneSuffix {

    @Override
    public String GUID() {
        return "Of Icestorms";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new WaterPeneFlat(), new WaterSpellToAttackFlat());

    }

}
