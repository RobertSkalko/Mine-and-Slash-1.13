package com.robertx22.mine_and_slash.database.stats;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.db_lists.newregistry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.newregistry.SlashRegistryType;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;

public abstract class StatMod implements IWeighted, IGUID, ISlashRegistryEntry<StatMod> {

    public float multiplier = 1F;

    public float sizeMultiplier() {
        return multiplier;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.STATMOD;
    }

    @Override
    public int Weight() {
        return IWeighted.UncommonWeight;
    }

    public abstract Stat GetBaseStat();

    public abstract float Min();

    public abstract float Max();

    public abstract StatTypes Type();

    @Override
    public String GUID() {
        return this.GetBaseStat().GUID() + "_" + this.Type().name();
    }

    public float GetFloatByPercent(int percent) {
        return (Min() + (Max() - Min()) * percent / 100) * multiplier;
    }

    public StatMod multi(float multiplier) {
        this.multiplier = multiplier;
        return this;
    }

}