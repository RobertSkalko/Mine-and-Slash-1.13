package com.robertx22.mmorpg.registers.common;

import com.robertx22.uncommon.capability.*;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityRegister {
    public static void register() {

        CapabilityManager.INSTANCE.register(EntityData.UnitData.class, new EntityData.Storage(), EntityData.DefaultImpl::new);

        CapabilityManager.INSTANCE.register(PlayerMapData.IPlayerMapData.class, new PlayerMapData.Storage(), PlayerMapData.DefaultImpl::new);

        CapabilityManager.INSTANCE.register(MapData.IMapData.class, new MapData.Storage(), MapData.DefaultImpl::new);

        CapabilityManager.INSTANCE.register(MasterLootBagCap.IMasterLootBagData.class, new MasterLootBagCap.Storage(), MasterLootBagCap.DefaultImpl::new);

        CapabilityManager.INSTANCE.register(PlayerCapBackupData.IPlayerCapBackupData.class, new PlayerCapBackupData.Storage(), PlayerCapBackupData.DefaultImpl::new);

    }
}
