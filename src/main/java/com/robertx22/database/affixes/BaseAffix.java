package com.robertx22.database.affixes;

import com.robertx22.database.IGUID;
import com.robertx22.database.stats.StatMod;
import com.robertx22.db_lists.bases.IhasRequirements;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.interfaces.IAutoLocName;
import com.robertx22.uncommon.interfaces.IWeighted;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public abstract class BaseAffix implements IWeighted, IGUID, IAutoLocName, IhasRequirements {

    public BaseAffix() {
    }

    public abstract String GUID();

    @Override
    public int Weight() {
        return IWeighted.UncommonWeight;
    }

    public abstract List<StatMod> StatMods();

    @Override
    public ITextComponent locName() {
        return CLOC.blank(locNameLangFileGUID(formattedGUID()));
    }
}
