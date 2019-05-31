package com.robertx22.database.stats.stat_types.elementals;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.TransferMethod;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IGenerated;
import com.robertx22.uncommon.interfaces.IStatTransfer;
import com.robertx22.uncommon.utilityclasses.StatUtils;

import java.util.ArrayList;
import java.util.List;

public class BaseTransferMod extends Stat implements IStatTransfer, IGenerated<Stat> {

    @Override
    public String locDescForLangFile() {
        return "Transfer takes a % from 1 and gives to the other";
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
        return null;
    }

    @Override
    public void transferStats(Unit copy, Unit unit, StatData data) {

        for (TransferMethod stat : this.Transfer()) {

            float val = copy.MyStats.get(stat.converted.GUID()).Flat * data.Value /* percent */ / 100;

            unit.MyStats.get(stat.converted.GUID()).Flat -= val;
            unit.MyStats.get(stat.statThatBenefits.GUID()).Flat += val;

        }

    }

    List<TransferMethod> transfers = new ArrayList<>();

    public BaseTransferMod() {
    }

    public BaseTransferMod(Elements from, Elements to) {
        this.fromElement = from;
        this.toElement = to;
        this.maximumValue = 100;
        this.GUID = from.name() + "_to_" + to.name() + "_transfer";
        this.GUID = GUID.toLowerCase();

        transfers.add(new TransferMethod(StatUtils.AttackDamage.get(from), StatUtils.AttackDamage
                .get(to)));
        transfers.add(new TransferMethod(StatUtils.SpellDamage.get(from), StatUtils.SpellDamage
                .get(to)));

    }

    public String GUID;
    public Elements fromElement;
    public Elements toElement;

    @Override
    public List<TransferMethod> Transfer() {
        return transfers;

    }

    public List<Stat> generateAllPossibleStatVariations() {

        List<Stat> stats = new ArrayList<>();

        for (Elements from : Elements.values()) {
            if (from != Elements.None) {
                for (Elements to : Elements.values()) {
                    if (to != Elements.None && to != from) {
                        BaseTransferMod stat = new BaseTransferMod(from, to);
                        stats.add(stat);
                    }
                }
            }
        }
        return stats;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public String locNameForLangFile() {
        return "Transfer " + fromElement.name() + " to " + toElement.name();
    }

}
