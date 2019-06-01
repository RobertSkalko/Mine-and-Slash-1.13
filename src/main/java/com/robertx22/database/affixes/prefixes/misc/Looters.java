package com.robertx22.database.affixes.prefixes.misc;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.gearitemslots.Bracelet;
import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.generated.XBonusLootDropFlat;
import com.robertx22.uncommon.enumclasses.LootType;

import java.util.Arrays;
import java.util.List;

public class Looters extends Prefix {

    @Override
    public int Weight() {
        return LegendaryWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new SlotRequirement(new Bracelet()), LevelRequirement.fromLVL50());
    }

    @Override
    public String GUID() {
        return "looters";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new XBonusLootDropFlat(LootType.All));
    }

    @Override
    public String locNameForLangFile() {
        return "Looter's";
    }
}

