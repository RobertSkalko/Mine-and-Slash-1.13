package com.robertx22.database.stats.stat_mods.generated;

import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.generated.XBonusLootDrop;
import com.robertx22.uncommon.enumclasses.LootType;
import com.robertx22.uncommon.enumclasses.StatTypes;
import com.robertx22.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XBonusLootDropFlat extends StatMod implements IGenerated<StatMod> {

    public LootType type;
    public String GUID;

    public XBonusLootDropFlat() {

    }

    public XBonusLootDropFlat(LootType type) {
        this.type = type;
        this.GUID = "bonus_" + type + "_drops_flat";

    }

    @Override
    public Stat GetBaseStat() {
        return new XBonusLootDrop(type);
    }

    @Override
    public float Min() {
        return 10;
    }

    @Override
    public float Max() {
        return 20;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {
        List<StatMod> list = new ArrayList<>();
        Arrays.asList(LootType.values())
                .forEach(x -> list.add(new XBonusLootDropFlat(x)));
        return list;

    }
}