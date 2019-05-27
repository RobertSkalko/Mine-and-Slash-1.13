package com.robertx22.database.unique_items;

import com.robertx22.database.IGUID;
import com.robertx22.database.stats.StatMod;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.interfaces.ITiered;
import com.robertx22.uncommon.interfaces.IWeighted;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public interface IUnique extends IWeighted, ITiered, IGUID {

    @Override
    public default int Weight() {
        return this.UncommonWeight;
    }

    public default ITextComponent locName() {
        return CLOC.uniqueName(this.GUID());
    }

    public default ITextComponent locDesc() {
        return CLOC.uniqueDesc(this.GUID());
    }

    List<StatMod> uniqueStats();

    String slot();

}
