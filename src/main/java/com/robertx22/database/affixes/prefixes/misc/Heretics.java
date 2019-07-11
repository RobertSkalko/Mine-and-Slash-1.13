package com.robertx22.database.affixes.prefixes.misc;

import com.robertx22.database.affixes.ElementalPrefix;
import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.requirements.LevelRequirement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.requirements.SlotRequirement;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalAffinityFlat;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.Arrays;
import java.util.List;

public class Heretics extends ElementalPrefix {

    public Heretics(Elements element) {
        super(element);
    }

    @Override
    public Prefix newGeneratedInstance(Elements element) {
        return new Heretics(element);
    }

    @Override
    public String GUID() {
        return element.name() + "_heretic";
    }

    @Override
    public int Weight() {
        return IWeighted.EpicWeight;
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new CriticalDamageFlat().multi(1.5F), new ElementalAffinityFlat(element)
                .multi(0.25F));
    }

    @Override
    public Requirements requirements() {
        return new Requirements(LevelRequirement.fromLVL50(), SlotRequirement.jewerlyOnly());
    }

    @Override
    public String locNameForLangFile() {
        return element.dmgName + " Heretic";
    }

}
