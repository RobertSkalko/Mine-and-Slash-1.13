package com.robertx22.mmorpg.registers.common;

import com.robertx22.uncommon.capability.DimsData;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.WorldData;

import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityRegister {
    public static void register() {

	CapabilityManager.INSTANCE.register(EntityData.UnitData.class, new EntityData.Storage(),
		EntityData.DefaultImpl::new);

	CapabilityManager.INSTANCE.register(WorldData.IWorldData.class, new WorldData.Storage(),
		WorldData.DefaultImpl::new);

	CapabilityManager.INSTANCE.register(DimsData.IDimsData.class, new DimsData.Storage(),
		DimsData.DefaultImpl::new);

    }
}
