package com.robertx22.database.stats.stat_mods.generated;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.generated.BonusSpecificSpell;
import com.robertx22.db_lists.Spells;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class BonusSpecificSpellFlat extends StatMod implements IGenerated<StatMod> {

    public BaseSpell spell;

    public BonusSpecificSpellFlat(BaseSpell type) {
        this.spell = type;
    }

    @Override
    public float Min() {
        return 20;
    }

    @Override
    public float Max() {
        return 50;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new BonusSpecificSpell(spell);
    }

    @Override
    public String GUID() {
        return spell.formattedGUID() + "power_flat";
    }

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {
        List<StatMod> list = new ArrayList<>();
        Spells.All.values().forEach(x -> list.add(new BonusSpecificSpellFlat(x)));
        return list;
    }

}
