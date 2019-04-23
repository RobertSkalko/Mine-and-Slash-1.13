package com.robertx22.uncommon.datasaving;

import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class Load {

    public static LazyOptional<UnitData> Unit(ICapabilityProvider provider) {
	if (provider != null) {
	    return provider.getCapability(EntityData.Data);
	}
	return null;
    }

    public static LazyOptional<IWorldData> World(ICapabilityProvider provider) {

	if (provider != null) {
	    return provider.getCapability(WorldData.Data);

	}
	return null;
    }
}
