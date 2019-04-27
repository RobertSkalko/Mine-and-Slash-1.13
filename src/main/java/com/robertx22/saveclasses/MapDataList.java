package com.robertx22.saveclasses;

import java.util.HashMap;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class MapDataList {

    @Store
    public HashMap<String, DimensionData> dimDatas = new HashMap<String, DimensionData>();

}
