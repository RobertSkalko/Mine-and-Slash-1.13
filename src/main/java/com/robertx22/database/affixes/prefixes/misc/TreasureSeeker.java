package com.robertx22.database.affixes.prefixes.misc;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.gearitemslots.Bracelet;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.generated.LootTypeBonusFlat;
import com.robertx22.uncommon.enumclasses.LootType;

import java.util.Arrays;
import java.util.List;

public class TreasureSeeker extends Prefix {

    @Override
    public int Weight() {
        return this.RareWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new SlotRequirement(new Bracelet()));
    }

    @Override
    public String GUID() {
        return "TreasureSeeker";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new LootTypeBonusFlat(LootType.Map));
    }

    @Override
    public String locNameForLangFile() {
        return "Treasure Seeker's";
    }
}

