package com.robertx22.database.rarities.containers;

import com.robertx22.database.rarities.MapRarity;
import com.robertx22.database.rarities.RaritiesContainer;
import com.robertx22.database.rarities.maps.*;

import java.util.Arrays;
import java.util.List;

public class MapRarities extends RaritiesContainer<MapRarity> {

    public static final List<MapRarity> Maps = Arrays.asList(new CommonMap(), new UncommonMap(), new RareMap(), new EpicMap(), new LegendaryMap(), new MythicalMap());

    @Override
    public List<MapRarity> rarities() {
        return Maps;
    }
}
