package com.mmorpg_libraries.curios;

import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.event.LivingCurioChangeEvent;

@Mod.EventBusSubscriber
public class OnCurioChangEvent {
    @SubscribeEvent
    public static void onEquipCurioChange(LivingCurioChangeEvent event) {

        EntityLivingBase entity = event.getEntityLiving();
        if (entity != null) {
            EntityData.UnitData data = Load.Unit(entity);
            data.setEquipsChanged(true);
            data.recalculateStats(entity);

        }
    }
}
