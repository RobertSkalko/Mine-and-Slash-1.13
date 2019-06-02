package com.robertx22.database.stats.stat_types.generated;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.database.stats.stat_types.ElementalStat;
import com.robertx22.database.stats.stat_types.core_stats.ICoreStat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class ElementalAffinity extends ElementalStat implements ICoreStat {

    public ElementalAffinity(Elements element) {
        super(element);

    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public boolean ScalesToLevel() {
        return true;
    }

    @Override
    public Stat newStatInstance(Elements element) {
        return new ElementalAffinity(element);
    }

    @Override
    public float amountToReach100Percent() {
        return 0;
    }

    @Override
    public void addToOtherStats(EntityData.UnitData unitdata, StatData data) {

        for (StatMod statmod : this.statsThatBenefit()) {
            StatModData.Load(statmod, (int) data.Value).useOnPlayer(unitdata);
        }

    }

    @Override
    public String locDescForLangFile() {
        return "Increases Resist and Spell Damage of that element.";
    }

    @Override
    public String GUID() {
        return Element().toString() + "_affinity";
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(new ElementalResistFlat(this.Element()), new ElementalSpellDamageFlat(this
                .Element()));
    }

    @Override
    public String locNameForLangFile() {
        return Element().name() + " Affinity";
    }

}
