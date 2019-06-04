package com.robertx22.database.stats.stat_mods.flat.corestats;

import com.robertx22.database.stats.IAmountsGenerated;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.StatModSizes;
import com.robertx22.database.stats.stat_types.core_stats.AllAttributes;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.interfaces.IWeighted;

public class AllAttributesFlat extends StatMod implements IAmountsGenerated {

    public StatModSizes size = StatModSizes.Medium;

    @Override
    public int Weight() {
        return IWeighted.LegendaryWeight;
    }

    public AllAttributesFlat() {

    }

    public AllAttributesFlat(StatModSizes size) {
        this.size = size;
    }

    @Override
    public Stat GetBaseStat() {
        return new AllAttributes();
    }

    @Override
    public float Min() {
        return 1 * this.size.multi;
    }

    @Override
    public float Max() {
        return 4 * this.size.multi;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return this.getGUIDPrefix() + "_all_attributes_flat";
    }

    @Override
    public StatModSizes modSize() {
        return size;
    }

    @Override
    public StatMod newGeneratedInstance(StatModSizes size) {
        return new AllAttributesFlat(size);
    }
}

