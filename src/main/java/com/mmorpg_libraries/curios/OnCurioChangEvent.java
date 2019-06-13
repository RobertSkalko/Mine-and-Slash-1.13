package com.mmorpg_libraries.curios;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class OnCurioChangEvent {
/*
    @SubscribeEvent
    public static void onEquipCurioChange(LivingCurioChangeEvent event) {

        LivingEntity entity = event.getEntityLiving();
        if (entity != null) {
            EntityData.UnitData data = Load.Unit(entity);
            data.setEquipsChanged(true);
            data.recalculateStats(entity);

            if (entity instanceof PlayerEntity) {
                data.syncToClient((PlayerEntity) entity);
            }
        }
    }
    
 */
}
