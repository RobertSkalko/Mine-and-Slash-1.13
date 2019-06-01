package com.robertx22.database.stats.stat_types.generated;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellNatureDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellThunderDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.elemental.spell_dmg.SpellWaterDamageFlat;
import com.robertx22.database.stats.stat_types.core_stats.ICoreStat;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class XElementAffinity extends Stat implements ICoreStat, IGenerated<Stat> {

    public Elements element;

    public XElementAffinity() {

    }

    public static HashMap<Elements, List<StatMod>> statsThatBenefit = new HashMap<Elements, List<StatMod>>() {
        {
            {
                put(Elements.Fire, Arrays.asList(new FireResistFlat(), new SpellFireDamageFlat()));
                put(Elements.Water, Arrays.asList(new WaterResistFlat(), new SpellWaterDamageFlat()));
                put(Elements.Thunder, Arrays.asList(new ThunderResistFlat(), new SpellThunderDamageFlat()));
                put(Elements.Nature, Arrays.asList(new NatureResistFlat(), new SpellNatureDamageFlat()));
                put(Elements.None, Arrays.asList());

            }

        }
    };

    public String GUID;

    public XElementAffinity(Elements element) {
        this.element = element;
        this.GUID = element.toString() + "_affinity";

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
    public Elements Element() {
        return this.element;
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
        return this.GUID;
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return statsThatBenefit.get(this.Element());
    }

    @Override
    public String locNameForLangFile() {
        return Element().name() + " Affinity";
    }

    @Override
    public List<Stat> generateAllPossibleStatVariations() {
        List<Stat> list = new ArrayList<>();
        Elements.getAll().forEach(x -> list.add(new XElementAffinity(x)));
        return list;

    }
}
