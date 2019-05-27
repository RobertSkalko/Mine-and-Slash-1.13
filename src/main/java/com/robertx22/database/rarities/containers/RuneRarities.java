package com.robertx22.database.rarities.containers;

import com.robertx22.database.rarities.RaritiesContainer;
import com.robertx22.database.rarities.RuneRarity;
import com.robertx22.database.rarities.runes.*;

import java.util.Arrays;
import java.util.List;

public class RuneRarities extends RaritiesContainer<RuneRarity> {
    public static final List<RuneRarity> Runes = Arrays.asList(new CommonRune(), new UncommonRune(), new RareRune(), new EpicRune(), new LegendaryRune(), new MythicalRune());

    @Override
    public List<RuneRarity> rarities() {
        return Runes;
    }
}
