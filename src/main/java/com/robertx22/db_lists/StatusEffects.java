package com.robertx22.db_lists;

import com.robertx22.database.status_effects.*;
import com.robertx22.database.status_effects.bases.BaseStatusEffect;

import java.util.HashMap;

public class StatusEffects {
    public static HashMap<String, BaseStatusEffect> All = new HashMap<String, BaseStatusEffect>() {
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

}
