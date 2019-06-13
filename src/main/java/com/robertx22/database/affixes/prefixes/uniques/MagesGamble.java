package com.robertx22.database.affixes.prefixes.uniques;

import com.robertx22.database.affixes.Prefix;
import com.robertx22.database.requirements.ExactUniquesRequierement;
import com.robertx22.database.requirements.Requirements;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.generated.BonusSpecificSpellFlat;
import com.robertx22.database.unique_items.necklaces.MagesLuckyAmulet;
import com.robertx22.db_lists.Spells;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.interfaces.IGenerated;
import com.robertx22.uncommon.interfaces.IWeighted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MagesGamble extends Prefix implements IGenerated<Prefix> {

    public BaseSpell spell;

    public MagesGamble(BaseSpell spell) {
        this.spell = spell;
    }

    @Override
    public String GUID() {
        return "mages_gamble_" + spell.formattedGUID();
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".prefix." + "mages_gamble";
    }

    @Override
    public int Weight() {
        return IWeighted.UniqueSetSuperCommonWeight;
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new BonusSpecificSpellFlat(spell));
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new ExactUniquesRequierement(new MagesLuckyAmulet()));
    }

    @Override
    public String locNameForLangFile() {
        return "Mage's Gamble";
    }

    @Override
    public List<Prefix> generateAllPossibleStatVariations() {
        List<Prefix> list = new ArrayList<>();
        Spells.All.values().forEach(x -> list.add(new MagesGamble(x)));
        return list;

    }
}