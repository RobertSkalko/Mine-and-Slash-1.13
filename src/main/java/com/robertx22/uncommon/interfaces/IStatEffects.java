package com.robertx22.uncommon.interfaces;

import com.robertx22.database.IGUID;

import java.util.List;

public interface IStatEffects extends IGUID {

    public abstract List<IStatEffect> GetEffects();
}
