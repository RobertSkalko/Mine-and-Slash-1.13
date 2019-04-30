package com.robertx22.saveclasses;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.ArrayList;
import java.util.List;

@Storable
public class MapWorldPlayerListData {

    public MapWorldPlayerListData() {

    }

    @Store
    public List<String> joinedPlayerIDs = new ArrayList<String>();

}
