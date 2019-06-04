package com.robertx22.database.stats.stat_types.generated;

import com.robertx22.database.stats.ConversionMethod;
import com.robertx22.database.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IGenerated;
import com.robertx22.uncommon.interfaces.IStatConversion;

import java.util.ArrayList;
import java.util.List;

public class ElementalConversion extends Stat implements IStatConversion, IGenerated<Stat> {

    List<ConversionMethod> conversion = new ArrayList<>();

    public ElementalConversion(Elements from, Elements to) {
        this.fromElement = from;
        this.toElement = to;
        this.maximumValue = 100;
        this.GUID = from.name() + "_to_" + to.name() + "_conversion";
        this.GUID = GUID.toLowerCase();

        conversion.add(new ConversionMethod(new ElementalAttackDamage(from), new ElementalAttackDamage(to)));
        conversion.add(new ConversionMethod(new ElementalSpellDamage(from), new ElementalSpellDamage(to)));

    }

    public String GUID;
    public Elements fromElement;
    public Elements toElement;

    @Override
    public List<ConversionMethod> conversion() {
        return conversion;

    }

    public List<Stat> generateAllPossibleStatVariations() {

        List<Stat> stats = new ArrayList<>();

        for (Elements from : Elements.getAllSingleElements()) {
            if (from != Elements.None) {
                for (Elements to : Elements.getAllSingleElements()) {
                    if (to != Elements.None && to != from) {
                        ElementalConversion stat = new ElementalConversion(from, to);
                        stats.add(stat);
                    }
                }
            }
        }
        return stats;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public String locDescForLangFile() {
        return "Conversion adds a % of one to the other";
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public Elements Element() {
        return null;
    }

    @Override
    public String locNameForLangFile() {
        return "Convert " + fromElement.name() + " to " + toElement.name();
    }

}