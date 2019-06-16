package com.robertx22.database.unique_items.swords;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalAttackDamageFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.database.stats.stat_mods.percent.offense.CriticalDamagePercent;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.database.unique_items.bases.BaseUniqueSword;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IGenerated;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElementalSaber extends BaseUniqueSword implements IGenerated<IUnique> {

    public Elements element;

    public ElementalSaber(Elements element) {
        this.element = element;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(element).multi(0.5F), new CriticalHitFlat()
                .multi(2), new CriticalDamagePercent(), new ElementalSpellDamageFlat(element)
                .multi(2));
    }

    @Override
    public String locDescForLangFile() {
        return "Let " + element.name() + " guide you.";
    }

    @Override
    public String locNameForLangFile() {
        return TextFormatting.YELLOW + "Saber of " + element.dmgName + " madness";
    }

    @Override
    public String GUID() {
        return element.name().toLowerCase() + "_ele_saber0";
    }

    @Override
    public int Weight() {
        return LegendaryWeight;
    }

    @Override
    public int Tier() {
        return 14;
    }

    @Override
    public List<IUnique> generateAllPossibleStatVariations() {
        List<IUnique> list = new ArrayList<>();
        Elements.getAllSingleElements().forEach(x -> list.add(new ElementalSaber(x)));
        return list;
    }
}
