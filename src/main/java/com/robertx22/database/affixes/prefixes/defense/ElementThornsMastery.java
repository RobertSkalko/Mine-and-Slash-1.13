package com.robertx22.database.affixes.prefixes.defense;

import com.robertx22.database.affixes.ElementalPrefix;
import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.requirements.ExactUniquesRequierement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.database.stats.stat_mods.generated.StatDoublePercent;
import com.robertx22.database.stats.stat_types.generated.BlockReflect;
import com.robertx22.database.unique_items.shields.ShieldElemental;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElementThornsMastery extends ElementalPrefix {

    public ElementThornsMastery(Elements element) {
        super(element);
    }

    @Override
    public Prefix newGeneratedInstance(Elements element) {
        return new ElementThornsMastery(element);
    }

    @Override
    public String GUID() {
        return "Bonus" + element.name() + "ReflectPrefix";
    }

    @Override
    public int Weight() {
        return IWeighted.LegendaryWeight;
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new StatDoublePercent(new BlockReflect(element)).multi(0.5F), new ElementalResistFlat(element));
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new ExactUniquesRequierement(new ShieldElemental(element).generateAllPossibleStatVariations()));
    }

    @Override
    public String locNameForLangFile() {
        return element.dmgName + " Thorns Mastery";
    }

    @Override
    public List<Prefix> generateAllPossibleStatVariations() {
        List<Prefix> list = new ArrayList<>();
        new ShieldElemental(element).generateAllPossibleStatVariations()
                .forEach(x -> list.add(newGeneratedInstance(((ShieldElemental) x).element)));
        return list;

    }
}
