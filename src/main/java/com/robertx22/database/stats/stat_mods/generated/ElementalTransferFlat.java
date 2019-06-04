package com.robertx22.database.stats.stat_mods.generated;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.generated.ElementalTransfer;
import com.robertx22.db_lists.Stats;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class ElementalTransferFlat extends StatMod implements IGenerated<StatMod> {

    public String GUID;
    public String BaseStatGUID;
    public Elements fromElement;
    public Elements toElement;

    public ElementalTransferFlat(Elements from, Elements to) {
        this.fromElement = from;
        this.toElement = to;
        this.GUID = from.name() + "To" + to.name() + "TransferFlat";

        ElementalTransfer stat = new ElementalTransfer(from, to);
        this.BaseStatGUID = stat.GUID();

    }

    @Override
    public float Min() {
        return 25;
    }

    @Override
    public float Max() {
        return 55;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return Stats.All.get(BaseStatGUID);
    }

    @Override
    public String GUID() {
        return GUID;
    }

    public List<StatMod> generateAllPossibleStatVariations() {

        List<StatMod> stats = new ArrayList<>();

        for (Elements from : Elements.getAllSingleElements()) {
            if (from != Elements.None) {
                for (Elements to : Elements.getAllSingleElements()) {
                    if (to != Elements.None && to != from) {
                        ElementalTransferFlat stat = new ElementalTransferFlat(from, to);
                        stats.add(stat);
                    }
                }
            }
        }
        return stats;
    }
}
