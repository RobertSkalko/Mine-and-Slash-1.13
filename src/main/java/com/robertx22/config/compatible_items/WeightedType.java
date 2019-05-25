package com.robertx22.config.compatible_items;

import com.robertx22.config.compatible_items.ConfigItem.creationTypes;
import com.robertx22.uncommon.interfaces.IWeighted;

public class WeightedType implements IWeighted {

    public WeightedType(int weight, creationTypes type) {
        this.weight = weight;
        this.type = type;
    }

    public int weight;

    public creationTypes type;

    @Override
    public int Weight() {
        return weight;
    }

}