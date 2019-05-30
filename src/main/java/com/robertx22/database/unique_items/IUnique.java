package com.robertx22.database.unique_items;

import com.robertx22.database.IGUID;
import com.robertx22.database.stats.StatMod;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.interfaces.IAutoLocDesc;
import com.robertx22.uncommon.interfaces.IAutoLocName;
import com.robertx22.uncommon.interfaces.ITiered;
import com.robertx22.uncommon.interfaces.IWeighted;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public interface IUnique extends IWeighted, ITiered, IGUID, IAutoLocName, IAutoLocDesc {

    @Override
    public default int Weight() {
        return this.UncommonWeight;
    }

    List<StatMod> uniqueStats();

    String slot();

    @Override
    public default ITextComponent locName() {
        return CLOC.blank(locNameLangFileGUID(GUIDFormatted()));
    }

    @Override
    public default ITextComponent locDesc() {
        return CLOC.blank(locNameLangFileGUID(GUIDFormatted()));
    }

    public default ITextComponent getStatDescription() {
        return CLOC.blank(locDescLangFileGUID(GUIDFormatted()));
    }

    @Override
    public default String locNameLangFileGUID(String guid) {
        return "item." + Ref.MODID + ".uniques." + slot().toLowerCase() + "." + guid;
    }

    @Override
    public default String locDescLangFileGUID(String guid) {
        return Ref.MODID + ".uniques.tooltip." + guid;
    }

}
