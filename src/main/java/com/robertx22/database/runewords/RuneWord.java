package com.robertx22.database.runewords;

import com.robertx22.database.IGUID;
import com.robertx22.database.stats.StatMod;
import com.robertx22.items.runes.base.BaseRuneItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.interfaces.IAutoLocName;
import com.robertx22.uncommon.interfaces.IWeighted;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public abstract class RuneWord implements IGUID, IWeighted, IAutoLocName {

    public abstract List<StatMod> mods();

    public abstract String GUID();

    @Override
    public ITextComponent locName() {
        return CLOC.blank(locNameLangFileGUID(GUIDFormatted()));
    }

    @Override
    public String locNameLangFileGUID(String guid) {
        return Ref.MODID + ".word." + guid;
    }

    public abstract List<BaseRuneItem> runes();

    public int size() {
        return runes().size();
    }

    @Override
    public int Weight() {
        return 1000;
    }

    public String getRuneWordCombo() {

        String text = "";

        for (BaseRuneItem item : runes()) {
            text += item.name().toUpperCase();
        }
        return text;
    }

    public String getRuneWordComboString() {

        String text = "";

        for (BaseRuneItem item : runes()) {
            text += item.name().toUpperCase() + " + ";
        }
        text = text.substring(0, text.length() - 3);

        return text;
    }

    public boolean runesMatch(String word) {
        return this.getRuneWordCombo().equals(word);
    }

}
