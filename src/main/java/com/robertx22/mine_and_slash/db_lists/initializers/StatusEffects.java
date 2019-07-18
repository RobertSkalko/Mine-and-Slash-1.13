package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.status_effects.*;
import com.robertx22.mine_and_slash.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.mine_and_slash.db_lists.newregistry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.newregistry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.newregistry.SlashRegistryType;

import java.util.HashMap;

public class StatusEffects implements ISlashRegistryInit {
    private static HashMap<String, BaseStatusEffect> All = new HashMap<String, BaseStatusEffect>() {
        {
            {
                put(new MobHealthSE().GUID(), new MobHealthSE());
                put(new MobElementResistSE().GUID(), new MobElementResistSE());
                put(new MobArmorSE().GUID(), new MobArmorSE());

                put(new MobNatureDMGSE().GUID(), new MobNatureDMGSE());
                put(new MobWaterDMGSE().GUID(), new MobWaterDMGSE());
                put(new MobThunderDMGSE().GUID(), new MobThunderDMGSE());
                put(new MobFireDMGSE().GUID(), new MobFireDMGSE());
                put(new MobLifestealSE().GUID(), new MobLifestealSE());

            }
        }
    };

    @Override
    public void registerAll() {
        All.values()
                .forEach(x -> SlashRegistry.getRegistry(SlashRegistryType.STATUS_EFFECT)
                        .register(x));

    }
}
