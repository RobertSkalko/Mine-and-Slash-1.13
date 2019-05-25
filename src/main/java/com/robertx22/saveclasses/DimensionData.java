package com.robertx22.saveclasses;

import com.robertx22.db_lists.WorldProviders;
import com.robertx22.dimensions.IWP;
import com.robertx22.dimensions.MapManager;
import info.loenwind.autosave.annotations.Factory;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;

@Storable
public class DimensionData {

    @Factory
    public DimensionData() {

    }

    public DimensionData(DimensionType type, IWP theclass) {

        this.IWPGuid = theclass.GUID();
        this.ResourceLocationString = MapManager.getResourceLocation(type).toString();

    }

    public DimensionData(String type, String theclass) {

        this.IWPGuid = theclass;
        this.ResourceLocationString = type;

    }

    public IWP getIWP() {
        return WorldProviders.All.get(this.IWPGuid);
    }

    @Store
    public String ResourceLocationString;

    @Store
    public String IWPGuid;

    public DimensionType getDimensionType() {

        DimensionType type = MapManager.getDimensionType(getResource());

        if (type == null) {
            new Exception("DIMENSION TYPE CAN'T BE NULL").printStackTrace();
        }

        return type;

    }

    public ResourceLocation getResource() {
        return new ResourceLocation(this.ResourceLocationString);
    }

}
