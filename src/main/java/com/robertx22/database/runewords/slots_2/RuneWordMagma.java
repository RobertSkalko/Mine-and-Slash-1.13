package com.robertx22.database.runewords.slots_2;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.items.runes.DosItem;
import com.robertx22.items.runes.VohItem;
import com.robertx22.items.runes.base.BaseRuneItem;
import com.robertx22.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class RuneWordMagma extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new ElementalResistFlat(Elements.Fire), new ArmorFlat());
    }

    @Override
    public String GUID() {
        return "Magma";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new DosItem(0), new VohItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Magma";
    }
}
