package com.robertx22.onevent.world;

import com.robertx22.uncommon.interfaces.IWeighted;
import net.minecraft.entity.EntityType;

public class WeightedMob implements IWeighted {

    EntityType type;
    int weight;

    public WeightedMob(EntityType type, int weight) {
        this.type = type;
        this.weight = weight;

    }

    @Override
    public int Weight() {
        return weight;
    }
}
