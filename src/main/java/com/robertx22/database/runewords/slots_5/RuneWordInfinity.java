package com.robertx22.database.runewords.slots_5;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stats.stat_mods.percent.EnergyRegenPercent;
import com.robertx22.items.runes.*;
import com.robertx22.items.runes.base.BaseRuneItem;

import java.util.Arrays;
import java.util.List;

public class RuneWordInfinity extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new EnergyFlat(), new EnergyRegenFlat(), new EnergyRegenPercent());
    }

    @Override
    public String GUID() {
        return "Infinity";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new CenItem(0), new DosItem(0), new AnoItem(0), new BerItem(0), new XahItem(0));
    }

}
