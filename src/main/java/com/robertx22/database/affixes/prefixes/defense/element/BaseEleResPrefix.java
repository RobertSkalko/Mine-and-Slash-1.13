package com.robertx22.database.affixes.prefixes.defense.element;

import com.robertx22.database.affixes.Prefix;

public abstract class BaseEleResPrefix extends Prefix {

    @Override
    public int Weight() {
        return this.EpicWeight;
    }

}
