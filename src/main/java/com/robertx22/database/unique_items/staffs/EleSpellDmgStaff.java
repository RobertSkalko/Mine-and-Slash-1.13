package com.robertx22.database.unique_items.staffs;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.offense.CompletePhysDispersionFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalAttackDamageFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalSpellDamagePercent;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.database.unique_items.bases.BaseUniqueStaff;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IGenerated;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EleSpellDmgStaff extends BaseUniqueStaff implements IGenerated<IUnique> {

    public Elements element;

    public EleSpellDmgStaff(Elements element) {
        this.element = element;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(element).multi(0.25F), new ElementalSpellDamagePercent(element)
                .multi(2), new ElementalSpellDamageFlat(element).multi(3.5F), new CompletePhysDispersionFlat());
    }

    @Override
    public String locDescForLangFile() {
        return "Do not be fooled from a glance.";
    }

    @Override
    public String locNameForLangFile() {
        return TextFormatting.YELLOW + "Rod of " + element.dmgName + "'s Requiem";
    }

    @Override
    public String GUID() {
        return element.name().toLowerCase() + "_ele_staff0";
    }

    @Override
    public int Weight() {
        return LegendaryWeight;
    }

    @Override
    public int Tier() {
        return 16;
    }

    @Override
    public List<IUnique> generateAllPossibleStatVariations() {
        List<IUnique> list = new ArrayList<>();
        Elements.getAllSingleElements().forEach(x -> list.add(new EleSpellDmgStaff(x)));
        return list;
    }
}
