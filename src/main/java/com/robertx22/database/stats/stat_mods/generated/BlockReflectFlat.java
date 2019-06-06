package com.robertx22.database.stats.stat_mods.generated;

import com.robertx22.database.ElementalStatMod;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_types.generated.BlockReflect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.enumclasses.StatTypes;

public class BlockReflectFlat extends ElementalStatMod {

    public BlockReflectFlat(Elements element) {
        super(element);
    }

    @Override
    public Stat GetBaseStat() {
        return new BlockReflect(element);
    }

    @Override
    public float Min() {
        return 2;
    }

    @Override
    public float Max() {
        return 5;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public StatMod newGeneratedInstance(Elements element) {
        return new BlockReflectFlat(element);
    }
}
