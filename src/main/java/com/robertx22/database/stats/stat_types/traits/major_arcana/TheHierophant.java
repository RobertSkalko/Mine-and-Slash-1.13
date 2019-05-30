package com.robertx22.database.stats.stat_types.traits.major_arcana;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.IntelligenceFlat;
import com.robertx22.database.stats.stat_mods.flat.corestats.WisdomFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.pene.NaturePeneFlat;
import com.robertx22.database.stats.stat_mods.multi.resources.ManaMulti;

import java.util.Arrays;
import java.util.List;

public class TheHierophant extends BaseMajorArcana {

    public static final String GUID = "TheHierophant";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new IntelligenceFlat(), new WisdomFlat(), new ManaMulti(), new NaturePeneFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "The Hierophant";
    }

}
