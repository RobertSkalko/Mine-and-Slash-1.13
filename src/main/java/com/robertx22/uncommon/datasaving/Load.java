package com.robertx22.uncommon.datasaving;

import com.robertx22.uncommon.capability.DimsData;
import com.robertx22.uncommon.capability.DimsData.IDimsData;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class Load {

    public static boolean hasUnit(ICapabilityProvider provider) { // tterag said this is correct
	if (provider != null) {
	    return provider.getCapability(EntityData.Data).isPresent();
	}
	return false;
    }

    public static UnitData Unit(ICapabilityProvider provider) {
	if (provider != null) {
	    return provider.getCapability(EntityData.Data).orElse(new EntityData.DefaultImpl());
	}
	return null;
    }

    public static IWorldData World(ICapabilityProvider provider) {

	if (provider != null) {
	    return provider.getCapability(WorldData.Data).orElse(new WorldData.DefaultImpl());

	}
	return null;
    }

    public static IDimsData Dims(ICapabilityProvider provider) {

	if (provider != null) {
	    return provider.getCapability(DimsData.Data).orElse(new DimsData.DefaultImpl());

	}
	return null;
    }

}
