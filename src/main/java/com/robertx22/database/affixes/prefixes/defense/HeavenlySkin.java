package com.robertx22.database.affixes.prefixes.defense;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.percent.ArmorPercent;
import com.robertx22.database.stats.stat_mods.percent.HealthPercent;

import java.util.Arrays;
import java.util.List;

public class HeavenlySkin extends Prefix {

    public HeavenlySkin() {
    }

    @Override
    public String GUID() {
        return "Heavenly Skin";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new ArmorPercent(), new HealthPercent());

    }

    @Override
    public int Weight() {
        return this.EpicWeight;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.armorsOnly());
    }

}
