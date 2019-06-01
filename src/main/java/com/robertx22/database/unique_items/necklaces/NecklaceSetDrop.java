package com.robertx22.database.unique_items.necklaces;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.corestats.AllAttributesFlat;
import com.robertx22.database.unique_items.bases.BaseUniqueNecklace;
import com.robertx22.uncommon.Styles;

import java.util.Arrays;
import java.util.List;

public class NecklaceSetDrop extends BaseUniqueNecklace {

    public NecklaceSetDrop() {

    }

    @Override
    public int Tier() {
        return 6;
    }

    @Override
    public boolean canGetSet() {
        return true;
    }

    @Override
    public String GUID() {
        return "necklacesetdrop0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new AllAttributesFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Amulet of The Seeker";
    }

    @Override
    public String locDescForLangFile() {
        return "I seek what is buried underneath.";
    }
}
