package com.robertx22.saveclasses;

import com.robertx22.db_lists.WorldProviders;
import com.robertx22.dimensions.IWP;
import com.robertx22.dimensions.MapManager;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;

@Storable
public class DimensionData {

    public DimensionData() {

    }

    public DimensionData(DimensionType type, IWP theclass) {

	this.IWPGuid = theclass.GUID();
	this.ResourceLocationString = type.getRegistryName().toString();

    }

    @Store
    public String ResourceLocationString;
    @Store
    public String IWPGuid;

    public DimensionType getDimensionType() {

	return MapManager.register(getResource(), WorldProviders.All.get(IWPGuid));

    }

    public ResourceLocation getResource() {
	return new ResourceLocation(this.ResourceLocationString);
    }

}
