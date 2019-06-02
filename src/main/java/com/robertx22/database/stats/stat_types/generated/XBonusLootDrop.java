package com.robertx22.database.stats.stat_types.generated;

import com.robertx22.database.stats.Stat;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.LootType;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XBonusLootDrop extends Stat implements IGenerated<Stat> {

    public LootType type;

    public XBonusLootDrop() {

    }

    public XBonusLootDrop(LootType type) {
        this.type = type;

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
    public String locDescForLangFile() {
        return "Increases Loot Drops of the type";
    }

    @Override
    public String GUID() {
        return "bonus_" + type + "_drops";
    }

    @Override
    public String locNameForLangFile() {
        return "Bonus " + type.getName() + " Drops";
    }

    @Override
    public List<Stat> generateAllPossibleStatVariations() {
        List<Stat> list = new ArrayList<>();
        Arrays.asList(LootType.values()).forEach(x -> list.add(new XBonusLootDrop(x)));
        return list;

    }
}
