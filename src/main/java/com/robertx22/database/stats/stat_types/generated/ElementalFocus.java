package com.robertx22.database.stats.stat_types.generated;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.offense.ElementalFocusEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IElementalGenerated;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class ElementalFocus extends Stat implements IElementalGenerated<Stat>, IStatEffects {
    public Elements element;

    public ElementalFocus(Elements element) {
        this.element = element;
    }

    @Override
    public String locNameForLangFile() {
        return element.name() + " Focus";
    }

    @Override
    public String GUID() {
        return element.name() + "_focus";
    }

    @Override
    public Stat newGeneratedInstance(Elements element) {
        return new ElementalFocus(element);
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
        return element;
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Dmg for that element by a % but decreases dmg from all other elements.";
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new ElementalFocusEffect());
    }
}
