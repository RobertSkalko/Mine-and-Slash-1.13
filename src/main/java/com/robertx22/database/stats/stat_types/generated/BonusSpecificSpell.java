package com.robertx22.database.stats.stat_types.generated;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_effects.offense.BonusSpecificSpellEffect;
import com.robertx22.db_lists.Spells;
import com.robertx22.items.spells.BaseSpellItem;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IGenerated;
import com.robertx22.uncommon.interfaces.IStatEffect;
import com.robertx22.uncommon.interfaces.IStatEffects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BonusSpecificSpell extends Stat implements IStatEffects, IGenerated<Stat> {

    private BaseSpell spell;

    public BonusSpecificSpell(BaseSpell type) {
        this.spell = type;
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Effect of that spell, whether that's damage, heal etc";
    }

    public BaseSpell spell() {
        return this.spell;
    }

    @Override
    public String GUID() {
        return this.spell.formattedGUID() + "_power";
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
        return spell.Element();
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new BonusSpecificSpellEffect(this.spell));
    }

    @Override
    public String locNameForLangFile() {
        BaseSpellItem item = (BaseSpellItem) this.spell.SpellItem();

        return item.locNameForLangFile() + " Power";
    }

    @Override
    public List<Stat> generateAllPossibleStatVariations() {
        List<Stat> list = new ArrayList<>();
        Spells.All.values().forEach(x -> list.add(new BonusSpecificSpell(x)));
        return list;

    }
}
