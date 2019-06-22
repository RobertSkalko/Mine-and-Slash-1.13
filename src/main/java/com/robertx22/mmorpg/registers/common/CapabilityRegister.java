package com.robertx22.mmorpg.registers.common;

import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.MapData;
import com.robertx22.uncommon.capability.PlayerMapData;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityRegister {
    public static void register() {

        CapabilityManager.INSTANCE.register(EntityData.UnitData.class, new EntityData.Storage(), EntityData.DefaultImpl::new);

        CapabilityManager.INSTANCE.register(PlayerMapData.IPlayerMapData.class, new PlayerMapData.Storage(), PlayerMapData.DefaultImpl::new);

        CapabilityManager.INSTANCE.register(MapData.IMapData.class, new MapData.Storage(), MapData.DefaultImpl::new);

    }
}
